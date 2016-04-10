package bg.jwd.spring.controller;

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
import bg.jwd.spring.dto.ProductTypeDTO;
import bg.jwd.spring.model.product.ProductType;
import bg.jwd.spring.service.IProductTypeService;


@Controller
public class ProductTypeController {

	private static final Logger logger = LoggerFactory.getLogger(ProductTypeController.class);

	@Inject
	private IProductTypeService productTypeService;

	// GET /product-type/new
	@RequestMapping(value = AppConstants.NEW_PRODUCT_TYPE_FRONT_END_PAGE, method = RequestMethod.GET)
	public String showNewRecordForm(Model model) {
		logger.info("Show - ProductType creation page!");

		return AppConstants.NEW_PRODUCT_TYPE_VIEW_NAME;
	}
	@RequestMapping(value = AppConstants.NEW_PRODUCT_TYPE_BACK_END_PAGE, method = RequestMethod.POST)
	public String createRecord(Model model, @ModelAttribute("productType")ProductTypeDTO productTypeDto) {
		logger.debug("POST - ProductType! ProductTypeDTO is {}.", productTypeDto);

		String name = productTypeDto.getName();
		if (name == null || name.isEmpty()) {
			model.addAttribute("errorMessage", "Form input field [productTypeName] is mandatory!");
			return AppConstants.NEW_PRODUCT_TYPE_VIEW_NAME;
		}

		ProductType productType = productTypeService.findProductTypeByName( name );
		if (productType == null) {
			productType = productTypeService.create(name, null );

			// TODO - Exception
			// org.springframework.web.util.NestedServletException: Request processing failed; nested exception is org.hibernate.HibernateException: Illegal attempt to associate a collection with two open sessions. Collection : [bg.jwd.spring.model.security.impl.UserImpl.roles#1]
//			productType.setCreatedBy( UserUtils.getCurrentUser() );

			productTypeService.saveProductType( productType );
		} else {
			model.addAttribute("errorMessage", "Product Type with the same name already exist!");
			return AppConstants.NEW_PRODUCT_TYPE_VIEW_NAME;
		}

		model.addAttribute("productTypeDTOs", productTypeService.getAll( null ));
		return "redirect:/" + AppConstants.PRODUCT_TYPE_REGISTER_FRONT_END_PAGE;
	}

	@RequestMapping(value = AppConstants.PRODUCT_TYPE_REGISTER_FRONT_END_PAGE, method = RequestMethod.GET)
	public String showRegister(Model model, @ModelAttribute(value="productTypeSearch")ProductTypeDTO searchPrototype) {
		logger.debug("GET  - ProductType Register! searchPrototype(ProductTypeDTO) is {}.", searchPrototype);

		model.addAttribute("productTypeDTOs", productTypeService.getAll( searchPrototype ) );
		return AppConstants.PRODUCT_TYPE_REGISTER_FRONT_END_PAGE;
	}

	@RequestMapping(value = AppConstants.EDIT_PRODUCT_TYPE_FRONT_END_PAGE, method = RequestMethod.GET)
	public String showEditRecordForm(Model model, @PathVariable("id") long productTypeId) {
		logger.debug("Show - ProductType EDIT page!");

		ProductType productType = productTypeService.findProductTypeById( productTypeId );
		if (productType == null) {
			model.addAttribute("errorMessage", "Path Variable [productTypeId] is invalid!");
			return AppConstants.PRODUCT_TYPE_REGISTER_FRONT_END_PAGE;
		}
		ProductTypeDTO productTypeDto = ProductTypeDTO.constructDto( productType );
		model.addAttribute("productType", productTypeDto);
		return AppConstants.EDIT_PRODUCT_TYPE_VIEW_NAME;
	}
	@Transactional
	@RequestMapping(value = AppConstants.EDIT_N_DELETE_PRODUCT_TYPE_BACK_END_PAGE, method = RequestMethod.POST)
	public String editRecord(Model model, @PathVariable("id") long productTypeId, @ModelAttribute("productType")ProductTypeDTO productTypeDto) {
		logger.debug("POST - ProductType! ProductTypeDTO is {}.", productTypeDto);

		ProductType productType = productTypeService.findProductTypeById( productTypeId );
		if (productType == null) {
			model.addAttribute("errorMessage", "ProductType with id["+productTypeId+"] do not exist!");
			model.addAttribute("productTypeDTOs", productTypeService.getAll( null ));
			return "redirect:/" + AppConstants.PRODUCT_TYPE_REGISTER_FRONT_END_PAGE;
		}

		String name = productTypeDto.getName();
		if (name == null || name.isEmpty()) {
			model.addAttribute("errorMessage", "Form input field [productTypeName] is mandatory!");
			return AppConstants.EDIT_PRODUCT_TYPE_FRONT_END_PAGE;
		}
		productType.setName( productTypeDto.getName() );
//		productType.setDescription( productTypeDto.getDescription() );

		// TODO - Exception
		// org.springframework.web.util.NestedServletException: Request processing failed; nested exception is org.hibernate.HibernateException: Illegal attempt to associate a collection with two open sessions. Collection : [bg.jwd.spring.model.security.impl.UserImpl.roles#1]
//		productType.setCreatedBy( UserUtils.getCurrentUser() );

		productTypeService.saveProductType( productType );

		model.addAttribute("productTypeDTOs", productTypeService.getAll( null ));
		return "redirect:/" + AppConstants.PRODUCT_TYPE_REGISTER_FRONT_END_PAGE;
	}

}