package bg.jwd.spring.dao.product;

import bg.jwd.spring.model.product.Product;


public interface IProductTypeDao {

	Product findByName(String name);

}