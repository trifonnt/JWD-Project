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


@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Inject
	private IUserDao userDao;


	@PostConstruct
	public void postConstruct() {
		logger.info("PostConstruct");
		logger.info("userDao = " + userDao);
	}

	public void setUserDaoImpl(IUserDao userDao) {
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