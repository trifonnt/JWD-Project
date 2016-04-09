package bg.jwd.spring.service;

import java.util.List;

import bg.jwd.spring.dto.OrderDTO;
import bg.jwd.spring.model.common.Location;
import bg.jwd.spring.model.order.Order;
import bg.jwd.spring.model.order.OrderLine;
import bg.jwd.spring.model.security.User;


public interface IOrderService {

	public Order createOrder(User customer, Location orderLocation, User creator);

	public OrderLine createOrderLine(Order order, User creator);

	public Order findById(long orderId);

	public Order findShoppingCartByCustomer(User customer);

	public List<Order> findOrdersByCustomer(User customer);

	public Order save(Order order);

	public List<OrderDTO> getAllAsDTO(OrderDTO searchPrototype);
}