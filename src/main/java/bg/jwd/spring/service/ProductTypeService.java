package bg.jwd.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import bg.jwd.spring.dao.product.impl.ProductTypeDaoImpl;
import bg.jwd.spring.dto.ProductTypeDTO;
import bg.jwd.spring.model.product.impl.ProductTypeImpl;
import bg.jwd.spring.model.security.impl.UserImpl;


@Service
public class ProductTypeService {

	protected static final Logger logger = LoggerFactory.getLogger(ProductTypeService.class);

	@Inject
	private ProductTypeDaoImpl productTypeDao;


	public ProductTypeImpl create(String name,  UserImpl creator) {
		return productTypeDao.createProductType( name, creator );
	}

	public ProductTypeImpl findProductTypeById(long id) {
		return productTypeDao.findById( id );
	}

	public ProductTypeImpl findProductTypeByName(String name) {
		return productTypeDao.findByName( name );
	}

	public List<ProductTypeImpl> findAll() {
		return productTypeDao.findAll();
	}

	public ProductTypeImpl saveProductType(ProductTypeImpl productType) {
		productTypeDao.saveOrUpdate( productType );
		return productType;
	}

	public List<ProductTypeDTO> getAll(ProductTypeDTO searchPrototype) {
		List<ProductTypeDTO> result = new ArrayList<ProductTypeDTO>();
		result = productTypeDao.getAllAsDTO( searchPrototype );
		
		return result;
	}
}