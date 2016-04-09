package bg.jwd.spring.dao.security;

import java.util.List;

import bg.jwd.spring.dto.CustomerDTO;
import bg.jwd.spring.model.security.Role;
import bg.jwd.spring.model.security.User;


public interface IUserDao {

	User createUser(String username, String password, List<Role> roles);

	User findByUsername(String username);

	List<CustomerDTO> getAllAsDTO(CustomerDTO searchPrototype);
}