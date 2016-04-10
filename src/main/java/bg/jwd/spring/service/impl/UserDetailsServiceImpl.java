package bg.jwd.spring.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.jwd.spring.dao.security.IUserDao;
import bg.jwd.spring.dao.security.IUserRoleDao;
import bg.jwd.spring.dao.security.impl.UserDaoImpl;
import bg.jwd.spring.model.security.CurrentUser;
import bg.jwd.spring.model.security.Role;
import bg.jwd.spring.model.security.User;


@Transactional(readOnly = true)
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Inject
	private IUserDao userDao;

	@Inject
	private IUserRoleDao userRoleDao;


	@PostConstruct
	public void postConstruct() {
		logger.debug("PostConstruct");
		logger.debug("userDao = " + userDao);
		logger.debug("userRoleDao = " + userRoleDao);
	}

	public void setUserDaoImpl(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername( username );
		if (user == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		List<Role> userRoles = userRoleDao.findByUser( user );
		return new CurrentUser(user, userRoles);
	}
}