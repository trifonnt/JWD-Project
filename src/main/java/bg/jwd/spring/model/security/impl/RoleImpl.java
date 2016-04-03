package bg.jwd.spring.model.security.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import bg.jwd.spring.model.security.IRole;


@Entity
@Table(name = "ws_role")
public class RoleImpl implements IRole {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "active")
	private boolean active;

	@Column(name = "name")
	private String name;

	
	public RoleImpl() {
		this("anonymous");
	}
	public RoleImpl(String name) {
		this.name = name;
		active = true;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean isActive() {
		return active;
	}
	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String getName() {
		return name;
	}
	@Override
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