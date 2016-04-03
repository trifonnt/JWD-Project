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
import bg.jwd.spring.dto.ProductTypeDTO;
import bg.jwd.spring.service.ProductService;
import bg.jwd.spring.service.ProductTypeService;


@Controller
public class ProductTypeController {

	private static final Logger logger = LoggerFactory.getLogger(ProductTypeController.class);

	@SuppressWarnings("unused")
	@Inject
	private ProductService productService;

	@Inject
	private ProductTypeService productTypeService;


	@RequestMapping(value = AppConstants.PRODUCT_TYPE_REGISTER_FRONT_END_PAGE, method = RequestMethod.GET)
	public String showRegister(Model model, @ModelAttribute(value="productTypeSearch")ProductTypeDTO searchPrototype) {
		logger.debug("GET  - ProductType Register! searchPrototype(ProductTypeDTO) is {}.", searchPrototype);

		model.addAttribute("productTypeDTOs", productTypeService.getAll( searchPrototype ) );
		return AppConstants.PRODUCT_TYPE_REGISTER_FRONT_END_PAGE;
	}
}