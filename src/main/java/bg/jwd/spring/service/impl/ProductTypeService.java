package bg.jwd.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import bg.jwd.spring.dao.product.impl.ProductTypeDaoImpl;
import bg.jwd.spring.dto.ProductTypeDTO;
import bg.jwd.spring.model.product.ProductType;
import bg.jwd.spring.model.security.User;
import bg.jwd.spring.service.IProductTypeService;


@Service
public class ProductTypeService implements IProductTypeService {

	protected static final Logger logger = LoggerFactory.getLogger(ProductTypeService.class);

	@Inject
	private ProductTypeDaoImpl productTypeDao;


	@Override
	public ProductType create(String name,  User creator) {
		return productTypeDao.createProductType( name, creator );
	}

	@Override
	public ProductType findProductTypeById(long id) {
		return productTypeDao.findById( id );
	}

	@Override
	public ProductType findProductTypeByName(String name) {
		return productTypeDao.findByName( name );
	}

	@Override
	public List<ProductType> findAll() {
		return productTypeDao.findAll();
	}

	@Override
	public ProductType saveProductType(ProductType productType) {
		productTypeDao.saveOrUpdate( productType );
		return productType;
	}

	@Override
	public List<ProductTypeDTO> getAll(ProductTypeDTO searchPrototype) {
		List<ProductTypeDTO> result = new ArrayList<ProductTypeDTO>();
		result = productTypeDao.getAllAsDTO( searchPrototype );
		
		return result;
	}
}