package bg.jwd.spring.service;

import java.util.List;

import bg.jwd.spring.dto.ProductTypeDTO;
import bg.jwd.spring.model.product.ProductType;
import bg.jwd.spring.model.security.User;

public interface IProductTypeService {

	ProductType create(String name, User creator);

	ProductType findProductTypeById(long id);

	ProductType findProductTypeByName(String name);

	List<ProductType> findAll();

	ProductType saveProductType(ProductType productType);

	List<ProductTypeDTO> getAll(ProductTypeDTO searchPrototype);

}