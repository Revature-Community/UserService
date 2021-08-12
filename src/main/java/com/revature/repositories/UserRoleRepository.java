package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.UserRole;

public interface UserRoleRepository extends JpaRepository <UserRole, Integer> {

}
