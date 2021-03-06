package bg.jwd.spring.dao.security.impl;

import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bg.jwd.spring.dao.AbstractHibernateDAO;
import bg.jwd.spring.dao.security.IRoleDao;
import bg.jwd.spring.model.security.Role;


@Repository(value="roleDaoImpl")
public class RoleDaoImpl 
	extends AbstractHibernateDAO<Role>
	implements IRoleDao
{

	protected static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

	private static AtomicLong idCounter = null;


	public RoleDaoImpl() {
		setClazz( Role.class );
	}

	@PostConstruct
	@Transactional(readOnly = true)
	public void postConstruct() {
		logger.debug("PostConstruct");
		logger.debug("sessionfactory = {}", sessionFactory);
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

	@Override
	@Transactional(readOnly = true)
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
/*
	@Override
	@Transactional(readOnly = true)
	public List<Role> findByUser(User user) {
		if (user == null || user.getId() <= 0) {
			throw new IllegalArgumentException("User MUST not be null!");
		}
		StringBuffer sql = new StringBuffer("SELECT r.id, r.name "
				+ "FROM ws_user_role ur "
				+ "INNER JOIN ws_role r ON (ur.role_id = r.id) "
				+ "WHERE ur.user_id = :user_id "
				+ "ORDER BY r.id ASC "
		);		SQLQuery query = getSession().createSQLQuery( sql.toString() );
		query.setParameter("user_id", user.getId() );
		query.addScalar("id", new LongType()).addScalar("name")
			.setResultTransformer( Transformers.aliasToBean(Role.class));

		@SuppressWarnings("unchecked")
		List<Role> result = query.list();
		logger.info("--- FOUND User Roles: " + result);
		return result;
	}
*/
}