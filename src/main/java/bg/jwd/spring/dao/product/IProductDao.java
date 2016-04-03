package bg.jwd.spring.dao.product;

import bg.jwd.spring.model.product.impl.ProductImpl;


public interface IProductDao {

	ProductImpl findByProductNumber(String productNumber);

}