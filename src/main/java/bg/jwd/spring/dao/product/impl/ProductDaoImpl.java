package bg.jwd.spring.dao.product.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.LongType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bg.jwd.spring.dao.AbstractHibernateDAO;
import bg.jwd.spring.dao.product.IProductDao;
import bg.jwd.spring.dto.ProductDTO;
import bg.jwd.spring.model.product.ProductType;
import bg.jwd.spring.model.product.Product;
import bg.jwd.spring.model.security.User;


@Repository(value="productDaoImpl")
public class ProductDaoImpl
	extends AbstractHibernateDAO<Product>
	implements IProductDao
{

	protected static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

	private static AtomicLong idCounter = null;

	public ProductDaoImpl() {
		setClazz(Product.class );
	}


	@PostConstruct
	@Transactional(readOnly = true)
	public void postConstruct() {
		logger.info("PostConstruct");
//		Criteria criteria = getSession()
//				.createCriteria( clazz )
//			    .setProjection(Projections.max("id"));
//		idCounter = (Long)criteria.uniqueResult();
		idCounter = new AtomicLong(
			(Long)getSession()
				.createSQLQuery("SELECT COALESCE(MAX(id), 0) AS max_id FROM ws_product ")
				.addScalar("max_id", new org.hibernate.type.LongType())
				.list().get(0)
			);
		logger.info("idCounter = {}", idCounter);
	}

	@Override
	public Product createProduct(String productNumber, String name, ProductType type, User creator) {
		if (productNumber == null || productNumber.isEmpty()) {
			throw new IllegalArgumentException("ProductNumber MUST not be null!");
		}
		User currentCreator = creator;
//		if (currentCreator == null) {
//			currentCreator = (UserImpl)UserUtils.getCurrentUser();
//		}
		Product product = new Product(productNumber, name, type, currentCreator);
		product.setId( idCounter.incrementAndGet() );
		return product;
	}

	@Override
	@Transactional(readOnly = true)
	public Product findByNumber(String number) {
		if (number == null || number.isEmpty()) {
			throw new IllegalArgumentException("ProductNumber MUST not be empty!");
		}
		String hql = "FROM " + clazz.getName() + " WHERE number = :number";
		Product result = (Product) getSession().createQuery( hql )
			.setString("number", number)
			.uniqueResult();
		logger.info("--- FOUND Product: " + result);
		return result;
	}
	@Override
	@Transactional(readOnly = true)
	public List<Product> findByName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name MUST not be empty!");
		}
		String hql = "FROM " + clazz.getName() + " WHERE name = :name";
		@SuppressWarnings("unchecked")
		List<Product> result = getSession().createQuery( hql )
			.setString("name", name)
			.list();
		logger.info("--- FOUND Product: " + result);
		return result;
	}

/*
SELECT prd.id, prd_number, prd.name, prd.description, type.name AS prd_type, price, qty_on_hand, creator.user_name AS creator_name
FROM ws_product prd
LEFT OUTER JOIN ws_product_type type ON (prd.type_id = type.id)
LEFT OUTER JOIN ws_user creator ON (prd.creator_id = creator.id)
WHERE 1 = 1
 AND type.name LIKE '%02%'
 */
	// http://stackoverflow.com/questions/3435588/hibernate-sqlquery-extract-variable
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<ProductDTO> getAllAsDTO(ProductDTO searchPrototype) {
		StringBuffer sql = new StringBuffer("SELECT prd.id, prd_number AS productNumber, prd.name, prd.description, type.name AS typeName"
				+ ", price AS priceBd, qty_on_hand AS qtyOnHandBd, creator.user_name AS creatorName "
				+ "FROM ws_product prd "
				+ "LEFT OUTER JOIN ws_product_type type ON (prd.type_id = type.id) "
				+ "LEFT OUTER JOIN ws_user creator ON (prd.creator_id = creator.id) "
				+ "WHERE 1 = 1 "
		);
		if (searchPrototype != null) {
			if (searchPrototype.getProductNumber() != null && !searchPrototype.getProductNumber().isEmpty()) {
				sql.append(" AND prd.prd_number LIKE :productNumber ");
			}
			if (searchPrototype.getName() != null  && !searchPrototype.getName().isEmpty()) {
				sql.append(" AND prd.name LIKE :productName ");
			}
			if (searchPrototype.getDescription() != null  && !searchPrototype.getDescription().isEmpty()) {
				sql.append(" AND prd.description LIKE :productDescription ");
			}
			if (searchPrototype.getTypeName() != null  && !searchPrototype.getTypeName().isEmpty()) {
				sql.append(" AND type.name LIKE :typeName ");
			}
		}
		sql.append(" ORDER BY prd.id ASC ");
		SQLQuery query = getSession().createSQLQuery( sql.toString() );
		if (searchPrototype != null) {
			if (searchPrototype.getProductNumber() != null && !searchPrototype.getProductNumber().isEmpty()) {
				query.setParameter("productNumber", searchPrototype.getProductNumber() );
			}
			if (searchPrototype.getName() != null  && !searchPrototype.getName().isEmpty()) {
				query.setParameter("productName", searchPrototype.getName() );
			}
			if (searchPrototype.getDescription() != null  && !searchPrototype.getDescription().isEmpty()) {
				query.setParameter("productDescription", searchPrototype.getDescription() );
			}
			if (searchPrototype.getTypeName() != null  && !searchPrototype.getTypeName().isEmpty()) {
				query.setParameter("typeName", searchPrototype.getTypeName() );
			}
		}
		query.addScalar("id", new LongType()).addScalar("productNumber").addScalar("name").addScalar("description").addScalar("typeName")
			.addScalar("priceBd", new BigDecimalType()).addScalar("qtyOnHandBd", new BigDecimalType()).addScalar("creatorName")
			.setResultTransformer( Transformers.aliasToBean(ProductDTO.class));

		List<ProductDTO> result = query.list();
//		ProductDTO dto = (ProductDTO) result.get(0);
		return result;
	}
}