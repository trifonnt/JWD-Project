package bg.jwd.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import bg.jwd.spring.dao.product.impl.ProductDaoImpl;
import bg.jwd.spring.dto.ProductDTO;
import bg.jwd.spring.model.product.IProductType;
import bg.jwd.spring.model.product.impl.ProductImpl;
import bg.jwd.spring.model.security.impl.UserImpl;


@Service
public class ProductService {

	protected static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Inject
	private ProductDaoImpl productDao;


	public ProductImpl createProduct(String productNumber, String name, IProductType type, UserImpl creator) {
		return productDao.createProduct(productNumber, name, type, creator);
	}

	public ProductImpl findProductById(long productId) {
		return productDao.findById( productId );
	}

	public ProductImpl findProductByNumber(String productNumber) {
		return productDao.findByNumber( productNumber );
	}

	public List<ProductImpl> findProductByName(String name) {
		return productDao.findByName( name );
	}

	public ProductImpl saveProduct(ProductImpl product) {
		productDao.saveOrUpdate( product );
		return product;
	}

	public List<ProductDTO> getAllProducts(ProductDTO searchPrototype) {
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		result = productDao.getAllAsDTO( searchPrototype );
		
		return result;
	}
}