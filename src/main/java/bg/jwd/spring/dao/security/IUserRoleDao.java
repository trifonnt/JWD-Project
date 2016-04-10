package bg.jwd.spring.dao.security;

import java.util.Collection;
import java.util.List;

import bg.jwd.spring.model.security.Role;
import bg.jwd.spring.model.security.User;
import bg.jwd.spring.model.security.UserRole;


public interface IUserRoleDao {

	List<Role> findByUser(User user);

	void saveOrUpdate(UserRole userRole);

	void saveOrUpdate(Collection<UserRole> userRoles);

}