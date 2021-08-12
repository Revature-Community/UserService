package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "user_roles")
public class UserRole {

	
	@Column(name = "userId", nullable = false)
	private Long id;
	
	
	@Column(name="role_id")
	private Integer role_id;


	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserRole(Long id, Integer role_id) {
		super();
		this.id = id;
		this.role_id = role_id;
	}


	@Override
	public String toString() {
		return "UserRole [id=" + id + ", role_id=" + role_id + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, role_id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		return Objects.equals(id, other.id) && Objects.equals(role_id, other.role_id);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getRole_id() {
		return role_id;
	}


	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}


	
	
	
}
