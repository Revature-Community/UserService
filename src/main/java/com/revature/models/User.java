package com.revature.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
public @Data class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId", nullable = false)
	private Long id;
	@Column(unique = true, nullable = false)
	private String email;

	@Column(name = "username", nullable = false)
	private String username;
//	@ColumnTransformer(
//		    read =  "pgp_sym_decrypt(" +
//		            "    pass, " +
//		            "    'encrypt.key'" +
//		            ")",
//		    write = "pgp_sym_encrypt( " +
//		            "    ?, " +
//		            "    'encrypt.key'" +
//		            ") "
//		)
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();


	// Orignal code from previous batch below vv
	
//	@Column(name = "role_id")
//	private int roleId;

//	@ManyToOne(fetch = FetchType.EAGER, optional = false)
//	@JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false, updatable = false)
//	private Role role;
//
//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//	@JsonManagedReference
//	private List<Response> response;
//
//	//================ group 4 edits ======
//	@OneToMany(mappedBy="userId", fetch = FetchType.LAZY)
//	@JsonManagedReference
//	private List<Posts> posts;
//	//================ 
	
	
	public User(String email) {
		this.email = email;
	}


	
}