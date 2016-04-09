package bg.jwd.spring.dao.product;

import java.util.List;

import bg.jwd.spring.dto.ProductDTO;
import bg.jwd.spring.model.product.Product;
import bg.jwd.spring.model.product.ProductType;
import bg.jwd.spring.model.security.User;


public interface IProductDao {

	Product createProduct(String productNumber, String name, ProductType type, User creator);

	Product findByNumber(String productNumber);

	List<Product> findByName(String name);

	List<ProductDTO> getAllAsDTO(ProductDTO searchPrototype);
}