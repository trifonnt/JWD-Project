package bg.jwd.spring.dao.security;

import bg.jwd.spring.model.security.User;


public interface IUserDao {

	User findByUsername(String username);

}