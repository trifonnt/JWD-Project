package bg.jwd.spring.model.security;

import java.util.List;

import org.springframework.security.core.userdetails.User;


public class CurrentUser extends User {

	private static final long serialVersionUID = 1L;

	private bg.jwd.spring.model.security.User user;


	public CurrentUser(bg.jwd.spring.model.security.User user, List<Role> userRoles) {
//		super(user.getUsername(), user.getPassword(), user.getAuthorities());
		super(user.getUsername(), user.getPassword(), userRoles);
		
		this.user = user;
	}

	public bg.jwd.spring.model.security.User getUser() {
		return user;
	}

	public String getFullName() {
		return user.getFirstName() + ' ' + user.getLastName();
	}

	public long getId() {
		return user.getId();
	}
}