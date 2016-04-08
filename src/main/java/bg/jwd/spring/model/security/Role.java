package bg.jwd.spring.model.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "ws_role")
public class Role
	implements GrantedAuthority, Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "active")
	private boolean active;

	@Column(name = "name")
	private String name;

	
	public Role() {
		this("anonymous");
	}
	public Role(String name) {
		this.name = name;
		active = true;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String getAuthority() {
		return name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RoleImpl [id=" + id
				+ ", active=" + active
				+ ", name=" + name
		+ "]";
	}
}