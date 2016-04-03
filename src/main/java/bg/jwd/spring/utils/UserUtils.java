package bg.jwd.spring.utils;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import bg.jwd.spring.model.security.User;


public class UserUtils {

	protected static final Logger logger = LoggerFactory.getLogger(UserUtils.class);

	private UserUtils() {
	}

	public static String getCurrentUserName() {
		String currentUserName = "anonymous";
		if (UserUtils.getCurrentUser() != null) {
			currentUserName = UserUtils.getCurrentUser().getUsername();
		}
		return currentUserName;
	}

	public static User getCurrentUser() {
		Object principal;

		try {
			principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (NullPointerException e) {
			return null;
		}

		if (principal == null || !(principal instanceof User)) {
			return null;
		}

		return (User) principal;
	}

	public static boolean hasRole(String roleName) {
		if (roleName == null || roleName.isEmpty()) {
			throw new IllegalArgumentException("RoleName is mandatory!");
		}
		boolean hasRole = false;
		if (UserUtils.getCurrentUser() != null) {
			User currentUser = UserUtils.getCurrentUser();
			Collection<? extends GrantedAuthority> permissions = currentUser.getAuthorities();
			for (GrantedAuthority currentPermission : permissions) {
				if (roleName.equals( currentPermission.getAuthority()) ) {
					hasRole = true;
					break;
				}
			}
		}
		return hasRole;
	}
}