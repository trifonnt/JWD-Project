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
import bg.jwd.spring.dto.ProductDTO;
import bg.jwd.spring.model.product.ProductType;
import bg.jwd.spring.model.product.Product;
import bg.jwd.spring.service.IProductTypeService;
import bg.jwd.spring.service.IProductService;


@Controller
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Inject
	private IProductService productService;

	@Inject
	private IProductTypeService productTypeService;

//	@Inject
//	private UserService userService;

	// GET /product/new/{id}
	// SERVER: localhost
	@RequestMapping(value = AppConstants.NEW_PRODUCT_FRONT_END_PAGE, method = RequestMethod.GET)
	public String showNewRecordForm(Model model, @PathVariable("typeId") long typeId) {
		logger.info("Show - Product creation page!");

		ProductType productType = productTypeService.findProductTypeById( typeId );
		if (productType == null) {
			model.addAttribute("errorMessage", "Path Variable [productType] is invalid!");
			return AppConstants.PRODUCT_REGISTER_FRONT_END_PAGE;
		}
		model.addAttribute("productTypeId", typeId);
		model.addAttribute("productTypeName", productType.getName());
		return AppConstants.NEW_PRODUCT_VIEW_NAME;
	}
	@Transactional
	@RequestMapping(value = AppConstants.NEW_PRODUCT_BACK_END_PAGE, method = RequestMethod.POST)
	public String createRecord(Model model, @ModelAttribute("product")ProductDTO productDto) {
		logger.debug("POST - Product! ProductDTO is {}.", productDto);

		String number = productDto.getProductNumber();
		if (number == null || number.isEmpty()) {
			model.addAttribute("errorMessage", "Form input field [productNumber] is mandatory!");
			return AppConstants.NEW_PRODUCT_TYPE_VIEW_NAME;
		}

		String name = productDto.getName();
		if (name == null || name.isEmpty()) {
			model.addAttribute("errorMessage", "Form input field [productName] is mandatory!");
			return AppConstants.NEW_PRODUCT_TYPE_VIEW_NAME;
		}

		String typeName = productDto.getTypeName();
		if (typeName == null || typeName.isEmpty()) {
			model.addAttribute("errorMessage", "Form input field [typeName] is mandatory!");
			return AppConstants.NEW_PRODUCT_TYPE_VIEW_NAME;
		}
		ProductType productType = productTypeService.findProductTypeByName(typeName);
		if (productType == null) {
			model.addAttribute("errorMessage", "Wrong value of input field [typeName]!");
			return AppConstants.NEW_PRODUCT_TYPE_VIEW_NAME;
		}

		Product product = productService.findProductByNumber( number );
		if (product == null) {
			product = productService.createProduct(number, name, productType, null);
			product.setDescription( productDto.getDescription() );
			product.setType( productType );
			product.setPrice( productDto.getPriceBd() );
			product.setQtyOnHand( productDto.getQtyOnHandBd() );

			// TODO - Exception
			// org.springframework.web.util.NestedServletException: Request processing failed; nested exception is org.hibernate.HibernateException: Illegal attempt to associate a collection with two open sessions. Collection : [bg.jwd.spring.model.security.impl.UserImpl.roles#1]
//			product.setCreatedBy( UserUtils.getCurrentUser() );

			productService.saveProduct( product );
		}

		model.addAttribute("productDTOs", productService.getAllProducts( null ));
		return "redirect:/" + AppConstants.PRODUCT_REGISTER_FRONT_END_PAGE;
	}

	@RequestMapping(value = AppConstants.EDIT_PRODUCT_FRONT_END_PAGE, method = RequestMethod.GET)
	public String showEditRecordForm(Model model, @PathVariable("id") long productId) {
		logger.info("Show - Product EDIT page!");

		Product product = productService.findProductById( productId );
		if (product == null) {
			model.addAttribute("errorMessage", "Path Variable [productId] is invalid!");
			return AppConstants.PRODUCT_REGISTER_FRONT_END_PAGE;
		}
		ProductDTO productDto = ProductDTO.constructDto( product );
		model.addAttribute("product", productDto);
		return AppConstants.EDIT_PRODUCT_VIEW_NAME;
	}
	@Transactional
	@RequestMapping(value = AppConstants.EDIT_N_DELETE_PRODUCT_BACK_END_PAGE, method = RequestMethod.POST)
	public String editRecord(Model model, @PathVariable("id") long productId, @ModelAttribute("product")ProductDTO productDto) {
		logger.debug("POST - Product! ProductDTO is {}.", productDto);

		Product product = productService.findProductById( productId );
		if (product == null) {
			model.addAttribute("errorMessage", "Product with id["+productId+"] do not exist!");
			model.addAttribute("productDTOs", productService.getAllProducts( null ));
			return "redirect:/" + AppConstants.PRODUCT_REGISTER_FRONT_END_PAGE;
		}
		String number = productDto.getProductNumber();
		if (number == null || number.isEmpty()) {
			model.addAttribute("errorMessage", "Form input field [productNumber] is mandatory!");
			return AppConstants.EDIT_PRODUCT_FRONT_END_PAGE;
		}

		String name = productDto.getName();
		if (name == null || name.isEmpty()) {
			model.addAttribute("errorMessage", "Form input field [productName] is mandatory!");
			return AppConstants.EDIT_PRODUCT_FRONT_END_PAGE;
		}

		product.setNumber( productDto.getProductNumber() );
		product.setName( productDto.getName() );
		product.setDescription( productDto.getDescription() );
		product.setPrice( productDto.getPriceBd() );
		product.setQtyOnHand( productDto.getQtyOnHandBd() );

		// TODO - Exception
		// org.springframework.web.util.NestedServletException: Request processing failed; nested exception is org.hibernate.HibernateException: Illegal attempt to associate a collection with two open sessions. Collection : [bg.jwd.spring.model.security.impl.UserImpl.roles#1]
//			product.setCreatedBy( UserUtils.getCurrentUser() );

		productService.saveProduct( product );

		model.addAttribute("productDTOs", productService.getAllProducts( null ));
		return "redirect:/" + AppConstants.PRODUCT_REGISTER_FRONT_END_PAGE;
	}

	@RequestMapping(value = AppConstants.PRODUCT_REGISTER_FRONT_END_PAGE, method = RequestMethod.GET)
	public String showRegister(Model model, @ModelAttribute(value="productSearch")ProductDTO searchPrototype) {
		logger.debug("GET  - Product Register! searchPrototype(ProductDTO) is {}.", searchPrototype);

		model.addAttribute("productTypeDTOs", productTypeService.findAll() );
		model.addAttribute("productDTOs", productService.getAllProducts( searchPrototype ) ); // TODO - implement Search
		return AppConstants.PRODUCT_REGISTER_FRONT_END_PAGE;
	}
}