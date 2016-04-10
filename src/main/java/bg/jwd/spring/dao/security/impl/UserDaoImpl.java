package bg.jwd.spring.dao.security.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.ArrayType;
import org.hibernate.type.LongType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bg.jwd.spring.dao.AbstractHibernateDAO;
import bg.jwd.spring.dao.security.IUserDao;
import bg.jwd.spring.dto.CustomerDTO;
import bg.jwd.spring.dto.OrderDTO;
import bg.jwd.spring.model.security.Role;
import bg.jwd.spring.model.security.User;


@SuppressWarnings("unused")
@Repository(value="userDaoImpl")
public class UserDaoImpl
	extends AbstractHibernateDAO<User>
	implements IUserDao
{

	protected static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private static AtomicLong idCounter = null;


	public UserDaoImpl() {
		setClazz(User.class );
	}

	@PostConstruct
	@Transactional(readOnly = true)
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

	@Override
	public User createUser(String username, String password, List<Role> roles) {
		User user = new User( username, password, roles);
		user.setId( idCounter.incrementAndGet() );
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException("Username MUST not be empty!");
		}
		Session session = getSession();

		String hql = "FROM " + clazz.getName() + " WHERE username = :username";
		User result = (User) session.createQuery( hql )
			.setString("username", username)
			.uniqueResult();

		session.flush();
		logger.info("--- FOUND user: " + result);
		return result;
	}
/*
	public List<CustomerDTO> getAllAsDTO2(CustomerDTO searchPrototype) {
		Map<Long, CustomerDTO> dtosById = new LinkedHashMap<Long, CustomerDTO>();

		List<Object[]> rows = hibernateTemplate.find("select u.id, r from User u inner join u.roles as r");
		for (Object[] row : rows) {
		    Long id = (Long) row[0];
		    Role role = (Role) row[1];
		    CustomerDTO dto = dtosById.get(id);
		    if (dto == null) {
		        dto = new CustomerDTO( id );
		        dtosById.put(id, dto);
		    }
		    dto.addRole( role );
		}
		List<CustomerDTO> dtos = new ArrayList<CustomerDTO>(dtosById.values());
		return dtos;
	}
*/
	@Override
	@Transactional(readOnly = true)
	public List<CustomerDTO> getAllAsDTO(CustomerDTO searchPrototype) {
		Map<Long, CustomerDTO> dtosById = new LinkedHashMap<Long, CustomerDTO>();

		//   Hibernate query: new DTO clause not working
		// - http://stackoverflow.com/questions/11614854/hibernate-query-new-dto-clause-not-working
		// 
		//   Dozer mapping for Hibernate object to DTO
		// - http://stackoverflow.com/questions/2826313/dozer-mapping-for-hibernate-object-to-dto
		StringBuffer sql = new StringBuffer("SELECT u.id, u.user_name AS username"
				+ ", u.first_name AS firstName, u.middle_name AS middleName, u.last_name AS lastName, u.email "
				+ ", r.name AS roleName "
//				+ ", u.phone, creator.user_name AS creatorName "
				+ "FROM ws_user u "
				+ "LEFT OUTER JOIN ws_user_role ur ON (u.id = ur.user_id)"
				+ "LEFT OUTER JOIN ws_role r ON (ur.role_id = r.id) "
//				+ "LEFT OUTER JOIN ws_user creator ON (u.creator_id = creator.id) "
				+ "WHERE 1 = 1 "
		);
		if (searchPrototype != null) {
			if (searchPrototype.getId() != null && searchPrototype.getId() > 0 ) {
				sql.append(" AND u.id = :id ");
			}
			if (searchPrototype.getUsername() != null  && !searchPrototype.getUsername().isEmpty()) {
				sql.append(" AND u.user_name LIKE :username ");
			}
		}
		sql.append(" ORDER BY u.id ASC ");
		SQLQuery query = getSession().createSQLQuery( sql.toString() );
		if (searchPrototype != null) {
			if (searchPrototype.getId() > 0) {
				query.setParameter("id", searchPrototype.getId() );
			}
			if (searchPrototype.getUsername() != null  && !searchPrototype.getUsername().isEmpty()) {
				query.setParameter("username", searchPrototype.getUsername() );
			}
		}
		query.addScalar("id", new LongType()).addScalar("username")
			.addScalar("firstName").addScalar("middleName").addScalar("lastName").addScalar("email")
			.addScalar("roleName")
//			.addScalar("creatorName")
//			.setResultTransformer( Transformers.aliasToBean(CustomerDTO.class))
		;
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
//		System.err.println("TRIFON - RESULT: " + rows.toString());
//		for (Object[] element: rows) {
//			System.err.println("  TRIFON - element = " + element);
//		}
		for (Object[] row : rows) {
		    Long id = (Long) row[0];
		    String roleName = (String) row[6];
		    CustomerDTO dto = dtosById.get( id );
		    if (dto == null) {
		        dto = new CustomerDTO();
		        dto.setId( id );
		        dto.setUsername( (String)row[1] );
		        dto.setFirstName( (String)row[2] );
		        dto.setMiddleName( (String)row[3] );
		        dto.setLastName( (String)row[4] );
		        dto.setEmail( (String)row[5] );

		        dtosById.put(id, dto);
		    }
		    dto.addRole( roleName );
		}
		List<CustomerDTO> dtos = new ArrayList<CustomerDTO>( dtosById.values() );
		return dtos;
	}
}