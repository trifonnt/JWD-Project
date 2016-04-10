package bg.jwd.spring.dao.security.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bg.jwd.spring.dao.AbstractHibernateDAO;
import bg.jwd.spring.dao.security.IUserRoleDao;
import bg.jwd.spring.model.security.Role;
import bg.jwd.spring.model.security.User;
import bg.jwd.spring.model.security.UserRole;


@Repository(value="userRoleDaoImpl")
public class UserRoleDaoImpl 
	extends AbstractHibernateDAO<UserRole>
	implements IUserRoleDao
{

	protected static final Logger logger = LoggerFactory.getLogger(UserRoleDaoImpl.class);


	public UserRoleDaoImpl() {
		setClazz( UserRole.class );
	}

	@PostConstruct
	@Transactional(readOnly = true)
	public void postConstruct() {
		logger.debug("PostConstruct");
		logger.debug("sessionfactory = {}", sessionFactory);
	}

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

	@Override
	public void saveOrUpdate(Collection<UserRole> userRoles) {
		if (userRoles != null) {
			for (UserRole userRole: userRoles) {
				super.saveOrUpdate( userRole );
			}
		}
	}
}