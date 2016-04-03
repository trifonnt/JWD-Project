package bg.jwd.spring.model.security;

import java.io.Serializable;


public interface IRole extends Serializable {

	boolean isActive();
	void setActive(boolean active);

	String getName();
	void setName(String name);
}