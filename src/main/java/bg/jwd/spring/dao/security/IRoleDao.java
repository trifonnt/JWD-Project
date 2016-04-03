package bg.jwd.spring.dao.security;

import bg.jwd.spring.model.security.IRole;


public interface IRoleDao {

	IRole findByName(String name);

}