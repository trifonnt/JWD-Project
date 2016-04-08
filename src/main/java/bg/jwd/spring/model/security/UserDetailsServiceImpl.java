package bg.jwd.spring.model.security;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import bg.jwd.spring.dao.security.IUserDao;
import bg.jwd.spring.dao.security.impl.UserDaoImpl;


@SuppressWarnings("unused")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Inject
	private UserDaoImpl userDao; // TODO - use Java Interface(IUserDao)


	@PostConstruct
	public void postConstruct() {
		logger.debug("PostConstruct");
		logger.debug("userDao = " + userDao);
	}

	public void setUserDaoImpl(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername( username );
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("User not found!");
		}
	}
}