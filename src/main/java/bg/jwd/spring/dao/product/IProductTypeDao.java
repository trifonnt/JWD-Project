package bg.jwd.spring.dao.product;

import java.util.List;

import bg.jwd.spring.dto.ProductTypeDTO;
import bg.jwd.spring.model.product.ProductType;
import bg.jwd.spring.model.security.User;


public interface IProductTypeDao {

	ProductType createProductType(String name, User creator);

	ProductType findById(long id);

	ProductType findByName(String name);

	List<ProductType> findAll();

	void saveOrUpdate(ProductType productType);

	List<ProductTypeDTO> getAllAsDTO(ProductTypeDTO searchPrototype);


}