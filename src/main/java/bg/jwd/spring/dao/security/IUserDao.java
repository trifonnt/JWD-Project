package bg.jwd.spring.dao.security;

import bg.jwd.spring.model.security.impl.UserImpl;


public interface IUserDao {

	UserImpl findByUsername(String username);

}