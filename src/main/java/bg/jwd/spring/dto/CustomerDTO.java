package bg.jwd.spring.dto;

import java.util.ArrayList;
import java.util.Collection;

import bg.jwd.spring.model.security.Role;
import bg.jwd.spring.model.security.User;

public class CustomerDTO {

	private Long id;
	private String username;
	private String password;
	private String description;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String phone;
	private String creatorName;
	private Collection<String> roles;


	public CustomerDTO() {
		roles = new ArrayList<String>();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
/*	public void setId(String idStr) {
		if (idStr != null && !idStr.isEmpty()) {
			id = Integer.parseInt(idStr);
		}
	}
*/
	public String getUsername() {
		return username;
	}
	public void setUsername(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Collection<String> getRoles() {
		return roles;
	}
	public void setRoles(Collection<String> roles) {
		if (roles == null) {
			this.roles = new ArrayList<String>();
		} else {
			this.roles = roles;
		}
	}
	public void addRole(String role) {
		roles.add( role );
	}

	public static CustomerDTO constructDTO(User user) {
		CustomerDTO result = new CustomerDTO();
		if (user != null) {
			result.setId( user.getId() );
			result.setUsername( user.getUsername() );
//			result.setDescription( productType.getDescription() );
			result.setFirstName( user.getFirstName() );
			result.setMiddleName( user.getMiddleName() );
			result.setLastName( user.getLastName() );
			result.setEmail( user.getEmail() );
//			result.setPhone( user.getPhone );
			for (Role role: user.getRoles()) {
				result.addRole( role.getName() );
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "CustomerDTO ["
				+ "id=" + id
				+ ", username=" + username
				+ ", description=" + description
				+ ", firstName=" + firstName
				+ ", middleName=" + middleName
				+ ", lastName=" + lastName
				+ ", email=" + email
				+ ", phone=" + phone
				+ ", creatorName=" + creatorName
		+ "]";
	}
}