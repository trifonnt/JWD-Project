package bg.jwd.spring;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppConstants {

	protected static final Logger logger = LoggerFactory.getLogger(AppConstants.class);

	public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

	public static final String NEW_PRODUCT_FRONT_END_PAGE = "product/new/{typeId:[0-9]+}"; // GET form
	public static final String EDIT_PRODUCT_FRONT_END_PAGE = "product/{id:[0-9]+}/edit"; // GET form
	public static final String DELETE_PRODUCT_FRONT_END_PAGE = "product/{id:[0-9]+}/delete"; // GET form
	public static final String NEW_PRODUCT_BACK_END_PAGE = "product"; // POST content
	public static final String EDIT_PRODUCT_BACK_END_PAGE = "product/{id:[0-9]+}"; // PUT content
	public static final String PRODUCT_REGISTER_FRONT_END_PAGE = "product-register"; // GET list


	public static final String PRODUCT_TYPE_REGISTER_FRONT_END_PAGE = "product-type-register"; // GET list


	public static final String NEW_CUSTOMER_FRONT_END_PAGE = "customer/new"; // GET form
	public static final String EDIT_CUSTOMER_FRONT_END_PAGE = "customer/{id:[0-9]+}/edit"; // GET form
	public static final String DELETE_CUSTOMER_FRONT_END_PAGE = "customer/{id:[0-9]+}/delete"; // GET form
	public static final String NEW_CUSTOMER_BACK_END_PAGE = "customer"; // POST content
	public static final String EDIT_CUSTOMER_BACK_END_PAGE = "customer/{id:[0-9]+}"; // PUT content
	public static final String CUSTOMER_REGISTER_FRONT_END_PAGE = "customer-register"; // GET list

}