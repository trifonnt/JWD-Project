package bg.jwd.spring.controller;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bg.jwd.spring.AppConstants;
import bg.jwd.spring.dto.ProductDTO;
import bg.jwd.spring.model.order.Order;
import bg.jwd.spring.model.order.OrderLine;
import bg.jwd.spring.model.product.Product;
import bg.jwd.spring.model.security.User;
import bg.jwd.spring.service.IOrderService;
import bg.jwd.spring.service.IProductService;
import bg.jwd.spring.service.IUserService;
import bg.jwd.spring.utils.UserUtils;


@Controller
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Inject
	private IOrderService orderService;

	@Inject
	private IProductService productService;

	@Inject
	private IUserService userService;


	// GET /product/{id:[0-9]+}/order
	@Transactional
	@RequestMapping(value = AppConstants.ORDER_ADD_TO_BASKET_BACK_END_PAGE, method = RequestMethod.POST)
	public String addProductToBasket(Model model, @PathVariable("id") long productId) {
		logger.info("POST - Add Product to Basket!");

		Product product = productService.findProductById( productId );
		if (product == null) {
			model.addAttribute("errorMessage", "Path Variable [productId] is invalid!");
			return AppConstants.PRODUCT_REGISTER_FRONT_END_PAGE;
		}
		User customer = userService.findCustomerByName( UserUtils.getCurrentUser().getUsername() );

		Order basket = orderService.findShoppingCartByCustomer( customer );
		if (basket == null) {
			basket = orderService.createOrder(customer, null, null);
			basket.setCompleted( false );
		}
		OrderLine orderLine = orderService.createOrderLine(basket, null);
		orderLine.setProduct( product );
		orderLine.setPriceEntered( product.getPrice() );
		orderLine.setQtyOrdered( BigDecimal.ONE );
		orderService.save( basket );

		model.addAttribute("basket", basket);
		return "product-register";
	}


	@RequestMapping(value = AppConstants.ORDER_BASKET_REGISTER_FRONT_END_PAGE, method = RequestMethod.GET)
	public String showBasketRegister(Model model, @ModelAttribute(value="productSearch")ProductDTO searchPrototype) {
		logger.debug("GET  - Basket Register! searchPrototype(OrderLineDTO) is {}.", searchPrototype);

		model.addAttribute("productDTOs", productService.getAllProducts( searchPrototype ) );
		return AppConstants.ORDER_BASKET_REGISTER_FRONT_END_PAGE;
	}
}