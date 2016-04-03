package bg.jwd.spring.service;

import java.util.List;

import bg.jwd.spring.dto.ProductDTO;
import bg.jwd.spring.model.product.Product;
import bg.jwd.spring.model.product.ProductType;
import bg.jwd.spring.model.security.User;

public interface IProductService {

	public Product createProduct(String productNumber, String name, ProductType type, User creator);

	public Product findProductById(long productId);

	public Product findProductByNumber(String productNumber);

	public List<Product> findProductByName(String name);

	public Product saveProduct(Product product);

	public List<ProductDTO> getAllProducts(ProductDTO searchPrototype);
}