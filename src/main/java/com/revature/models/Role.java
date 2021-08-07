package com.revature.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Roles")
@NoArgsConstructor
public @Data class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private Integer id;
	
	//private Integer roleId;
	
	@Enumerated(EnumType.STRING)
	private RoleTitle title;

	public Role(RoleTitle title) {
		this.title = title;
	}

	
//	public Role(Integer i, String user) {
//		this.roleId = i;
//		this.title = user;
//	}

//	public Role() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
	
	
}
