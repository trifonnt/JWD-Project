package bg.jwd.spring;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppConstants {

	protected static final Logger logger = LoggerFactory.getLogger(AppConstants.class);

	public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

	public static final String NEW_PRODUCT_FRONT_END_PAGE = "product/new/{typeId:[0-9]+}";

	public static final String EDIT_PRODUCT_FRONT_END_PAGE = "product/{id:[0-9]+}/edit";

	public static final String DELETE_PRODUCT_FRONT_END_PAGE = "product/{id:[0-9]+}/delete";

	public static final String NEW_PRODUCT_BACK_END_PAGE = "product";

	public static final String EDIT_PRODUCT_BACK_END_PAGE = "product/{id:[0-9]+}";

	public static final String PRODUCT_REGISTER_FRONT_END_PAGE = "product-register";


	public static final String PRODUCT_TYPE_REGISTER_FRONT_END_PAGE = "product-type-register";


	public static final String CUSTOMER_REGISTER_FRONT_END_PAGE = "customer-register";

}