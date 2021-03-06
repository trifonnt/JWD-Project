package bg.jwd.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import bg.jwd.spring.dao.security.IRoleDao;
import bg.jwd.spring.dao.security.IUserDao;
import bg.jwd.spring.dao.security.IUserRoleDao;
import bg.jwd.spring.dto.CustomerDTO;
import bg.jwd.spring.model.security.Role;
import bg.jwd.spring.model.security.User;
import bg.jwd.spring.model.security.UserRole;
import bg.jwd.spring.service.IUserService;


@Service
public class UserServiceImpl implements IUserService {

	protected static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Inject
	private IUserDao userDao;

	@Inject
	private IRoleDao roleDao;

	@Inject
	private IUserRoleDao userRoleDao;


	@PreAuthorize(value="hasRole('ROLE_SHOP_EMPLOYEE')")
	public User createCustomer(String customerName) {
		if (customerName == null || customerName.isEmpty()) {
			throw new IllegalArgumentException("Customer Name MUST not be null!");
		}
		
		List<Role> roles = new ArrayList<Role>();
		roles.add( roleDao.findByName("ROLE_USER"));

		if (customerName.startsWith("admin")) {
			roles.add(roleDao.findByName("ROLE_SHOP_EMPLOYEE"));
		}

		// username...: <ANY>
		// pass.......: 123456 - hashed with MD5 -- TODO - use bcrypt
		User user = userDao.createUser( customerName, "e10adc3949ba59abbe56e057f20f883e", roles );
		return user;
	}

	@Override
	public User findCustomerById(long customerId) {
		return userDao.findById( customerId );
	}

	@Override
	public User findCustomerByName(String customerName) {
		if (customerName == null || customerName.isEmpty()) {
			throw new IllegalArgumentException("Customer Name MUST not be empty!");
		}
		return userDao.findByUsername( customerName );
	}

	@Override
	public User saveCustomer(User customer) {
		userDao.saveOrUpdate( customer );
		for ( Role role : customer.getRoles()) {
			UserRole userRole = new UserRole(customer.getId(), role.getId());
			userRoleDao.saveOrUpdate( userRole );
		}
		return customer;
	}

	@Override
	public List<CustomerDTO> getAll(CustomerDTO searchPrototype) {
		List<CustomerDTO> result = new ArrayList<CustomerDTO>();
		result = userDao.getAllAsDTO( searchPrototype );

		return result;
	}
}