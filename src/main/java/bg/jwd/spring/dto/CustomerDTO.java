package bg.jwd.spring.dto;

import bg.jwd.spring.model.security.impl.UserImpl;

public class CustomerDTO {

	private long id;
	private String username;
	private String description;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String phone;
	private String creatorName;


	public CustomerDTO() {
		
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String name) {
		this.username = name;
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

	public static CustomerDTO constructDto(UserImpl user) {
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