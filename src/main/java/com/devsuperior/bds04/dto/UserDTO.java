package com.devsuperior.bds04.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.devsuperior.bds04.entities.User;


public class UserDTO {
	
	private Long id;
	private String email;
		
	private List<String> roles = new ArrayList<>();

	public UserDTO(User entity) {
		id = entity.getId();
		email = entity.getEmail();
		for(GrantedAuthority role : entity.getRoles()) {
			roles.add(role.getAuthority());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
