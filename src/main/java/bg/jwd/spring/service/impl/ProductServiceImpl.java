package bg.jwd.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import bg.jwd.spring.dao.product.IProductDao;
import bg.jwd.spring.dto.ProductDTO;
import bg.jwd.spring.model.product.ProductType;
import bg.jwd.spring.model.product.Product;
import bg.jwd.spring.model.security.User;
import bg.jwd.spring.service.IProductService;


@Service
public class ProductServiceImpl implements IProductService {

	protected static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Inject
	private IProductDao productDao;


	@Override
	public Product createProduct(String productNumber, String name, ProductType type, User creator) {
		return productDao.createProduct(productNumber, name, type, creator);
	}

	@Override
	public Product findProductById(long productId) {
		return productDao.findById( productId );
	}

	@Override
	public Product findProductByNumber(String productNumber) {
		return productDao.findByNumber( productNumber );
	}

	@Override
	public List<Product> findProductByName(String name) {
		return productDao.findByName( name );
	}

	@Override
	public Product saveProduct(Product product) {
		productDao.saveOrUpdate( product );
		return product;
	}

	@Override
	public List<ProductDTO> getAllProducts(ProductDTO searchPrototype) {
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		result = productDao.getAllAsDTO( searchPrototype );
		
		return result;
	}
}