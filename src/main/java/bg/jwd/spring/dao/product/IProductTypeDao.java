package bg.jwd.spring.dao.product;

import bg.jwd.spring.model.product.ProductType;


public interface IProductTypeDao {

	ProductType findByName(String name);

}