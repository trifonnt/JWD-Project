package bg.jwd.spring.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bg.jwd.spring.AppConstants;
import bg.jwd.spring.dto.CustomerDTO;
import bg.jwd.spring.model.security.User;
import bg.jwd.spring.service.IUserService;


@Controller
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Inject
	private IUserService userService;


	// GET /customer/new
	@RequestMapping(value = AppConstants.NEW_CUSTOMER_FRONT_END_PAGE, method = RequestMethod.GET)
	public String showNewRecordForm(Model model) {
		logger.debug("Show - Customer creation page!");

		return "customer-new";
	}
	@RequestMapping(value = AppConstants.NEW_CUSTOMER_BACK_END_PAGE, method = RequestMethod.POST)
	public String createRecord(Model model, @ModelAttribute("customer")CustomerDTO customerDto) {
		logger.debug("POST - Customer! CustomerDTO is {}.", customerDto);

		String username = customerDto.getUsername();
		if (username == null || username.isEmpty()) {
			model.addAttribute("errorMessage", "Form input field [username] is mandatory!");
			return AppConstants.NEW_CUSTOMER_FRONT_END_PAGE;
		}

		User customer = userService.findCustomerByName( username );
		if (customer == null) {
			customer = userService.createCustomer( username );
			customer.setAccountNonExpired( true );
			customer.setAccountNonLocked( true );
			customer.setCredentialsNonExpired( true );
			customer.setEnabled( true );

			customer.setEmail( customerDto.getEmail() );
			customer.setEmailVerified( false );
			customer.setFirstName( customerDto.getFirstName() );
			customer.setMiddleName( customerDto.getMiddleName() );
			customer.setLastName( customerDto.getLastName() );
//			customer.setPassword( customerDto.getPassword() );
			// TODO - Exception
			// org.springframework.web.util.NestedServletException: Request processing failed; nested exception is org.hibernate.HibernateException: Illegal attempt to associate a collection with two open sessions. Collection : [bg.jwd.spring.model.security.impl.UserImpl.roles#1]
//			customer.setCreatedBy( UserUtils.getCurrentUser() );

			userService.saveCustomer( customer );
		}

		model.addAttribute("customerDTOs", userService.getAll( null ));
		return "redirect:/" + AppConstants.CUSTOMER_REGISTER_FRONT_END_PAGE;
	}

	@RequestMapping(value = AppConstants.EDIT_CUSTOMER_FRONT_END_PAGE, method = RequestMethod.GET)
	public String showEditRecordForm(Model model, @PathVariable("id") long customerId) {
		logger.debug("Show - Customer EDIT page!");

		User customer = userService.findCustomerById( customerId );
		if (customer == null) {
			model.addAttribute("errorMessage", "Path Variable [customerId] is invalid!");
			return AppConstants.CUSTOMER_REGISTER_FRONT_END_PAGE;
		}
		CustomerDTO customerDto = CustomerDTO.constructDTO( customer );
		model.addAttribute("customer", customerDto);
		return "customer-edit";
	}
	@RequestMapping(value = AppConstants.EDIT_N_DELETE_CUSTOMER_BACK_END_PAGE, method = RequestMethod.POST)
	public String editRecord(Model model, @PathVariable("id") long customerId, @ModelAttribute("customer")CustomerDTO customerDto) {
		logger.debug("POST - Customer! CustomerDTO is {}.", customerDto);

		User customer = userService.findCustomerById( customerId );
		if (customer == null) {
			model.addAttribute("errorMessage", "Customer with id["+customerId+"] do not exist!");
			model.addAttribute("customerDTOs", userService.getAll( null ));
			return "redirect:/" + AppConstants.CUSTOMER_REGISTER_FRONT_END_PAGE;
		}

		String name = customerDto.getUsername();
		if (name == null || name.isEmpty()) {
			model.addAttribute("errorMessage", "Form input field [Username] is mandatory!");
			return AppConstants.EDIT_CUSTOMER_FRONT_END_PAGE;
		}
		// TODO - find if new Username is already used by another Customer!!!

		customer.setUsername( customerDto.getUsername() );
		customer.setFirstName( customerDto.getFirstName() );
		customer.setMiddleName( customerDto.getMiddleName() );
		customer.setLastName( customerDto.getLastName() );
		customer.setEmail( customerDto.getEmail() );

		// TODO - Exception
		// org.springframework.web.util.NestedServletException: Request processing failed; nested exception is org.hibernate.HibernateException: Illegal attempt to associate a collection with two open sessions. Collection : [bg.jwd.spring.model.security.impl.UserImpl.roles#1]
//		customer.setCreatedBy( UserUtils.getCurrentUser() );

		userService.saveCustomer( customer );

		model.addAttribute("customerDTOs", userService.getAll( null ));
		return "redirect:/" + AppConstants.CUSTOMER_REGISTER_FRONT_END_PAGE;
	}

	@RequestMapping(value = AppConstants.CUSTOMER_REGISTER_FRONT_END_PAGE, method = RequestMethod.GET)
	public String showRegister(Model model, @ModelAttribute(value="customerSearch")CustomerDTO searchPrototype) {
		logger.debug("GET  - Customer Register! searchPrototype(CustomerDTO) is {}.", searchPrototype);

		model.addAttribute("customerDTOs", userService.getAll( searchPrototype ) );
		return AppConstants.CUSTOMER_REGISTER_FRONT_END_PAGE;
	}
}