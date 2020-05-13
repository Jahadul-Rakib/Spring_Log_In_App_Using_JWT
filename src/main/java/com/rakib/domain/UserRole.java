package com.rakib.domain;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table
public class UserRole implements GrantedAuthority {

    /**
	 * 
	 */
	//private static final long serialVersionUID = 858445346925299906L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	private String userRole;
	public UserRole() {
	}

/*	public static long getSerialVersionUID() {
		return serialVersionUID;
	}*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String getAuthority() {
		return this.userRole;
	}
}
