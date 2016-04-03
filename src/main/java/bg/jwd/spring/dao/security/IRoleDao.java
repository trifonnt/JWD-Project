package bg.jwd.spring.dao.security;

import bg.jwd.spring.model.security.Role;


public interface IRoleDao {

	Role findByName(String name);

}