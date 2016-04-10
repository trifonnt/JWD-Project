package bg.jwd.spring.model.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(value = UserRoleId.class)
@Table(name = "ws_user_role")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	long userId;

	@Id
	@Column(name = "role_id")
	long roleId;


	public UserRole() {
		
	}
	public UserRole(long userId, long roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
}