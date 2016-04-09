package bg.jwd.spring.dao.product.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bg.jwd.spring.dao.AbstractHibernateDAO;
import bg.jwd.spring.dao.product.IProductTypeDao;
import bg.jwd.spring.dto.ProductTypeDTO;
import bg.jwd.spring.model.product.ProductType;
import bg.jwd.spring.model.security.User;


@Repository(value="productTypeDaoImpl")
public class ProductTypeDaoImpl
	extends AbstractHibernateDAO<ProductType>
	implements IProductTypeDao
{

	protected static final Logger logger = LoggerFactory.getLogger(ProductTypeDaoImpl.class);

	private static AtomicLong idCounter = null;

	public ProductTypeDaoImpl() {
		setClazz(ProductType.class );
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
				.createSQLQuery("SELECT COALESCE(MAX(id), 0) AS max_id FROM ws_product_type ")
				.addScalar("max_id", new org.hibernate.type.LongType())
				.list().get(0)
			);
		logger.info("idCounter = {}", idCounter);
	}

	public ProductType createProductType(String name, User creator) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name MUST not be null!");
		}
//		UserImpl currentCreator = creator;
//		if (currentCreator == null) {
//			currentCreator = (UserImpl)UserUtils.getCurrentUser();
//		}
		ProductType productType = new ProductType( name );
		productType.setId( idCounter.incrementAndGet() );
		return productType;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductType findByName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name MUST not be empty!");
		}
		String hql = "FROM " + clazz.getName() + " WHERE name = :name";
		ProductType result = (ProductType) getSession().createQuery( hql )
			.setString("name", name)
			.uniqueResult();
		logger.info("--- FOUND ProductType: " + result);
		return result;
	}


	@Transactional(readOnly = true)
	public List<ProductTypeDTO> getAllAsDTO(ProductTypeDTO searchPrototype) {
		StringBuffer sql = new StringBuffer("SELECT t.id, t.name "
//				+ ", creator.user_name AS creatorName "
				+ "FROM ws_product_type t "
//				+ "LEFT OUTER JOIN ws_user creator ON (prd.creator_id = creator.id) "
				+ "WHERE 1 = 1 "
		);
		if (searchPrototype != null) {
			if (searchPrototype.getName() != null  && !searchPrototype.getName().isEmpty()) {
				sql.append(" AND t.name LIKE :name ");
			}
//			if (searchPrototype.getDescription() != null  && !searchPrototype.getDescription().isEmpty()) {
//				sql.append(" AND t.description LIKE :description ");
//			}
		}
		SQLQuery query = getSession().createSQLQuery( sql.toString() );
		if (searchPrototype != null) {
			if (searchPrototype.getName() != null  && !searchPrototype.getName().isEmpty()) {
				query.setParameter("name", searchPrototype.getName() );
			}
//			if (searchPrototype.getDescription() != null  && !searchPrototype.getDescription().isEmpty()) {
//				query.setParameter("description", searchPrototype.getDescription() );
//			}
		}
		query.addScalar("id", new LongType()).addScalar("name")
//			.addScalar("creatorName")
			.setResultTransformer( Transformers.aliasToBean(ProductTypeDTO.class));

		@SuppressWarnings("unchecked")
		List<ProductTypeDTO> result = query.list();
		return result;
	}
}