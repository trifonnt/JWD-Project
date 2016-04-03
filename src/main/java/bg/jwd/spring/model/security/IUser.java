package bg.jwd.spring.model.security;

import org.springframework.security.core.userdetails.UserDetails;


public interface IUser extends UserDetails {

	public String getFirstName();
	public void setFirstName(String name);

	public String getMiddleName();
	public void setMiddleName(String middleName);

	public String getLastName();
	public void setLastName(String lastName);

	public String getEmail();
	public void setEmail(String email);

	public boolean isEmailVerified();
	public void setEmailVerified(boolean emailVerified);
}