package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Role;
import com.revature.models.User;

public interface RoleRepository extends  JpaRepository<Role, Integer>{

}
