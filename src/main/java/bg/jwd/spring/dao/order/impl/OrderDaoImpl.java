package bg.jwd.spring.dao.order.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bg.jwd.spring.dao.AbstractHibernateDAO;
import bg.jwd.spring.dao.order.IOrderDao;
import bg.jwd.spring.dto.OrderDTO;
import bg.jwd.spring.model.common.Location;
import bg.jwd.spring.model.order.Order;
import bg.jwd.spring.model.order.OrderLine;
import bg.jwd.spring.model.security.User;


@Repository(value="orderDaoImpl")
public class OrderDaoImpl 
	extends AbstractHibernateDAO<Order>
	implements IOrderDao
{

	protected static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

	private static AtomicLong idCounter = null;
	private static AtomicLong idLineCounter = null;

	public OrderDaoImpl() {
		setClazz( Order.class );
	}

	@PostConstruct
	@Transactional(readOnly = true)
	public void postConstruct() {
		logger.debug("PostConstruct");
		logger.debug("sessionfactory = {}", sessionFactory);
//		Criteria criteria = getSession()
//				.createCriteria( clazz )
//			    .setProjection(Projections.max("id"));
//		idCounter = (Long)criteria.uniqueResult();
		idCounter = new AtomicLong(
			(Long)getSession()
				.createSQLQuery("SELECT COALESCE(MAX(id), 0) AS max_id FROM ws_order ")
				.addScalar("max_id", new org.hibernate.type.LongType())
				.list().get(0)
			);
		idLineCounter = new AtomicLong(
			(Long)getSession()
				.createSQLQuery("SELECT COALESCE(MAX(id), 0) AS max_id FROM ws_order_line ")
				.addScalar("max_id", new org.hibernate.type.LongType())
				.list().get(0)
			);
		logger.info("idCounter = {}", idCounter);
		logger.info("idLineCounter = {}", idLineCounter);
	}

	@Override
	public Order createOrder(User customer, Location orderLocation, User creator) {
		Session session = getSession();
		if (customer != null) {
//			getSession().refresh( customer );
			customer = (User) session.merge( customer );
		}
		if (creator !=  null) {
//			getSession().refresh( creator );
			creator = (User) session.merge( creator );
		}
		Order order = new Order(customer, orderLocation, creator);
		order.setId( idCounter.incrementAndGet() );
		order.setNumber( "BASKET-" + idCounter.get() );
		return order;
	}

	@Override
	public OrderLine createOrderLine(Order order, User creator) {
		if (creator !=  null) {
//			getSession().refresh( creator );
			creator = (User) getSession().merge( creator );
		}
		OrderLine orderLine = new OrderLine( order );
		orderLine.setNumber( String.valueOf(1 + order.getLines().size()) );
		orderLine.setId( idLineCounter.incrementAndGet() );
		orderLine.setCreatedBy( creator );
		return orderLine;
	}

	@Override
	@Transactional(readOnly = true)
	public Order findShoppingCartByCustomer(User customer) {
		if (customer == null || customer.getId() <=0 ) {
			throw new IllegalArgumentException("Customer MUST not be null!");
		}
		String hql = "FROM " + clazz.getName() + " WHERE orderPartner.id = :customerId AND completed = :completed";
		Order result = (Order) getSession().createQuery( hql )
			.setLong("customerId", customer.getId())
			.setBoolean("completed", false)
			.uniqueResult();
		logger.debug("--- FOUND Order: " + result);
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Order> findOrdersByCustomer(User customer) {
		if (customer == null || customer.getId() <=0 ) {
			throw new IllegalArgumentException("Customer MUST not be null!");
		}
		String hql = "FROM " + clazz.getName() + " WHERE orderPartner.id = :customerId AND completed = :completed";

		@SuppressWarnings("unchecked")
		List<Order> result = (List<Order>) getSession().createQuery( hql )
			.setLong("customerId", customer.getId())
			.setBoolean("completed", true)
			.list();
		logger.debug("--- FOUND List<Order>: " + result);
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrderDTO> getAllAsDTO(OrderDTO searchPrototype) {
		// TODO Auto-generated method stub
		return null;
	}
}