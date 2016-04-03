package bg.jwd.spring.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bg.jwd.spring.AppConstants;
import bg.jwd.spring.dto.CustomerDTO;
import bg.jwd.spring.service.IUserService;


@Controller
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Inject
	private IUserService userService;


	@RequestMapping(value = AppConstants.CUSTOMER_REGISTER_FRONT_END_PAGE, method = RequestMethod.GET)
	public String showRegister(Model model, @ModelAttribute(value="customerSearch")CustomerDTO searchPrototype) {
		logger.debug("GET  - Customer Register! searchPrototype(CustomerDTO) is {}.", searchPrototype);

		model.addAttribute("customerDTOs", userService.getAll( searchPrototype ) );
		return AppConstants.CUSTOMER_REGISTER_FRONT_END_PAGE;
	}
}