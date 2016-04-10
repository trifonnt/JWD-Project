package bg.jwd.spring.model.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;


@SuppressWarnings("unused")
@Entity
@Table(name = "ws_user")
public class User implements UserDetails, Serializable
{

	private static final long serialVersionUID = -5025098630443219650L;

	protected static final Logger logger = LoggerFactory.getLogger(User.class);

	@Id
	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//	@Convert(converter=BooleanToStringConverter.class) // Use it if you want to store boolean values in String column.
	@Column(name = "account_non_expired")
	private boolean accountNonExpired;

	@Column(name = "account_non_locked")
	private boolean accountNonLocked;

	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "user_name")
	private String username;

	@Column(name = "password")
	private String password;

	// TODO - Use Intermediate(Embeddable) Class
	// - http://what-when-how.com/hibernate/advanced-entity-association-mappings-hibernate/
//	@OneToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "ws_user_role"
//		, joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }
//		, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
	@Transient
	private Collection<Role> roles;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "email_verified")
	private boolean emailVerified;


	public User() {
		roles = new ArrayList<Role>();
	}
	public User(String username, String password, Collection<Role> roles) {
		this.enabled = true;
		this.accountNonExpired = true;
		this.credentialsNonExpired = true;
		this.accountNonLocked = true;
		this.username = username;
		this.password = password;
		if (roles == null) {
			this.roles = new ArrayList<Role>();
		} else {
			this.roles = roles;
		}
	}

	@PostConstruct
	public void postConstruct() {
		logger.info("PostConstruct");
		if (roles == null) {
			roles = new ArrayList<Role>();
		}
	}

//	@Override
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	@Transient
	public Collection<Role> getAuthorities() {
		return getRoles();
	}

	public void addRole(Role role) {
		if (role == null) {
			throw new IllegalArgumentException("Role MUST not be null!");
		} else {
			getRoles().add( role );
		}
	}
	public Collection<Role> getRoles() {
		if (roles == null) {
			roles = new ArrayList<Role>();
		}
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
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

	public boolean isEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
	@Override
	public String toString() {
		return "UserImpl [id=" + id + ", accountNonExpired=" + accountNonExpired
				+ ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired
				+ ", enabled=" + enabled + ", username=" + username + ", password=" + password
				+ ", roles=" + roles
				+ ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", email=" + email + ", emailVerified=" + emailVerified
		+ "]";
	}
}