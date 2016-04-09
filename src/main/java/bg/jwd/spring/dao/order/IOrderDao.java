package bg.jwd.spring.dao.order;

import java.util.List;

import bg.jwd.spring.dto.OrderDTO;
import bg.jwd.spring.model.common.Location;
import bg.jwd.spring.model.order.Order;
import bg.jwd.spring.model.order.OrderLine;
import bg.jwd.spring.model.security.User;


public interface IOrderDao {

	Order createOrder(User customer, Location orderLocation, User creator);

	OrderLine createOrderLine(Order order, User creator);

	Order findShoppingCartByCustomer(User customer);

	List<Order> findOrdersByCustomer(User customer);

	List<OrderDTO> getAllAsDTO(OrderDTO searchPrototype);
}