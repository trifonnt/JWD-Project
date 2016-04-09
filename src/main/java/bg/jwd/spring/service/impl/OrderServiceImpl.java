package bg.jwd.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import bg.jwd.spring.dao.order.IOrderDao;
import bg.jwd.spring.dto.OrderDTO;
import bg.jwd.spring.model.common.Location;
import bg.jwd.spring.model.order.Order;
import bg.jwd.spring.model.order.OrderLine;
import bg.jwd.spring.model.security.User;
import bg.jwd.spring.service.IOrderService;

@Service
public class OrderServiceImpl
	implements IOrderService
{

	protected static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Inject
	private IOrderDao orderDao;


	@Override
	public Order createOrder(User customer, Location orderLocation, User creator) {
		return orderDao.createOrder(customer, orderLocation, creator);
	}

	@Override
	public OrderLine createOrderLine(Order order, User creator) {
		return orderDao.createOrderLine(order, creator);
	}

	@Override
	public Order findById(long orderId) {
		return orderDao.findById( orderId );
	}

	@Override
	public Order findShoppingCartByCustomer(User customer) {
		return orderDao.findShoppingCartByCustomer( customer );
	}

	@Override
	public List<Order> findOrdersByCustomer(User customer) {
		return orderDao.findOrdersByCustomer( customer );
	}

	@Override
	public Order save(Order order) {
		orderDao.saveOrUpdate( order );
		return order;
	}

	@Override
	public List<OrderDTO> getAllAsDTO(OrderDTO searchPrototype) {
		List<OrderDTO> result = new ArrayList<OrderDTO>();
		result = orderDao.getAllAsDTO( searchPrototype );
		return result;
	}
}