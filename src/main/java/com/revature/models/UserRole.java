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

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "user_id", nullable = false)
	private Long user_id;
	
	
	@Column(name="role_id")
	private Integer role_id;


	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserRole(Long user_id, Integer role_id) {
		super();
		this.user_id = user_id;
		this.role_id = role_id;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public Integer getRole_id() {
		return role_id;
	}


	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}




	
	
	
}
