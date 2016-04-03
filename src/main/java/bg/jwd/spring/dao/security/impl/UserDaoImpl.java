package bg.jwd.spring.dao.security.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import bg.jwd.spring.dao.AbstractHibernateDAO;
import bg.jwd.spring.dto.CustomerDTO;
import bg.jwd.spring.model.security.Role;
import bg.jwd.spring.model.security.User;


@Repository(value="userDaoImpl")
public class UserDaoImpl
	extends AbstractHibernateDAO<User>
//	implements IUserDao 
{

	protected static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private static AtomicLong idCounter = null;


	public UserDaoImpl() {
		setClazz(User.class );
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
				.createSQLQuery("SELECT COALESCE(MAX(id), 0) AS max_id FROM ws_user ")
				.addScalar("max_id", new org.hibernate.type.LongType())
				.list().get(0)
			);
		logger.info("idCounter = {}", idCounter);
	}

	public User createUser(String username, String password, List<Role> roles) {
		User user = new User( username, password, roles);
		user.setId( idCounter.incrementAndGet() );
		return user;
	}

//	@Override
	public User findByUsername(String username) {
		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException("Username MUST not be empty!");
		}
		String hql = "FROM " + clazz.getName() + " WHERE username = :username";
		User result = (User) getSession().createQuery( hql )
			.setString("username", username)
			.uniqueResult();
		logger.info("--- FOUND user: " + result);
		return result;
	}

	public List<CustomerDTO> getAllAsDTO(CustomerDTO searchPrototype) {
		StringBuffer sql = new StringBuffer("SELECT u.id, u.user_name AS username"
				+ ", u.first_name AS firstName, u.middle_name AS middleName, u.last_name AS lastName, u.email "
//				+ ", u.phone, creator.user_name AS creatorName "
				+ "FROM ws_user u "
//				+ "LEFT OUTER JOIN ws_user creator ON (u.creator_id = creator.id) "
				+ "WHERE 1 = 1 "
		);
		if (searchPrototype != null) {
			if (searchPrototype.getUsername() != null  && !searchPrototype.getUsername().isEmpty()) {
				sql.append(" AND u.user_name LIKE :name ");
			}
//			if (searchPrototype.getDescription() != null  && !searchPrototype.getDescription().isEmpty()) {
//				sql.append(" AND t.description LIKE :description ");
//			}
		}
		SQLQuery query = getSession().createSQLQuery( sql.toString() );
		if (searchPrototype != null) {
			if (searchPrototype.getUsername() != null  && !searchPrototype.getUsername().isEmpty()) {
				query.setParameter("usrename", searchPrototype.getUsername() );
			}
//			if (searchPrototype.getDescription() != null  && !searchPrototype.getDescription().isEmpty()) {
//				query.setParameter("description", searchPrototype.getDescription() );
//			}
		}
		query.addScalar("id", new LongType()).addScalar("username")
			.addScalar("firstName").addScalar("middleName").addScalar("lastName").addScalar("email")
//			.addScalar("creatorName")
			.setResultTransformer( Transformers.aliasToBean(CustomerDTO.class));

		@SuppressWarnings("unchecked")
		List<CustomerDTO> result = query.list();
		return result;
	}
}