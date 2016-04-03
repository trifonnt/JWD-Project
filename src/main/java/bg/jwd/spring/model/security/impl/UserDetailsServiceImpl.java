package bg.jwd.spring.model.security.impl;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import bg.jwd.spring.dao.security.impl.UserDaoImpl;


@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Inject
	private UserDaoImpl userDaoImpl;


//	public UserDetailsServiceImpl() {
//		userDao = new UserDaoImpl();
//	}

	@PostConstruct
	public void postConstruct() {
		logger.info("PostConstruct");
		logger.info("userDao = " + userDaoImpl);
	}

	public void setUserDaoImpl(UserDaoImpl userDao) {
		this.userDaoImpl = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserImpl user = userDaoImpl.findByUsername( username );
		if (user != null) {
//			user.addAuthority( new SimpleGrantedAuthority("ROLE_USER") );
//			if (username.startsWith("admin")) {
//				user.addAuthority( new SimpleGrantedAuthority("ROLE_BANK_EMPLOYEE") );
//			}
			// username...: <ANY>
			// pass.......: 123456 - hashed with MD5 -- TODO - use bcrypt
//			return new UserImpl(username, "e10adc3949ba59abbe56e057f20f883e", authorities);
			return user;
		} else {
			throw new UsernameNotFoundException("User not found!");
		}
	}
/*
	boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    return new User(
            userAdmin.getLoginAdmin(), 
            userAdmin.getPasswordAdmin(), 
            enabled, 
            accountNonExpired, 
            credentialsNonExpired, 
            accountNonLocked,
            getAuthorities(userAdmin.getRole().getIdRole())
    );
*/
}