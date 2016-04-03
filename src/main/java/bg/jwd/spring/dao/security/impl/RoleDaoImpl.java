package bg.jwd.spring.dao.security.impl;

import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import bg.jwd.spring.dao.AbstractHibernateDAO;
import bg.jwd.spring.model.security.Role;

@Repository(value="roleDaoImpl")
public class RoleDaoImpl 
	extends AbstractHibernateDAO<Role>
//	implements IRoleDao 
{

	protected static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

	private static AtomicLong idCounter = null;


	public RoleDaoImpl() {
		setClazz( Role.class );
	}

	@PostConstruct
	public void postConstruct() {
		logger.info("PostConstruct");
		logger.info("sessionfactory = {}", sessionFactory);
//		Criteria criteria = getSession()
//				.createCriteria( clazz )
//			    .setProjection(Projections.max("id"));
//		idCounter = (Long)criteria.uniqueResult();
		idCounter = new AtomicLong(
			(Long)getSession()
				.createSQLQuery("SELECT COALESCE(MAX(id), 0) AS max_id FROM ws_role ")
				.addScalar("max_id", new org.hibernate.type.LongType())
				.list().get(0)
			);
		logger.info("idCounter = {}", idCounter);
	}

//	@Override
	public Role findByName(String roleName) {
		if (roleName == null || roleName.isEmpty()) {
			throw new IllegalArgumentException("Role name MUST not be empty!");
		}
		String hql = "FROM " + clazz.getName() + " WHERE name = :name";
		Role result = (Role) getSession().createQuery( hql )
			.setString("name", roleName)
			.uniqueResult();
		logger.info("--- FOUND Role: " + result);
		return result;
	}
}