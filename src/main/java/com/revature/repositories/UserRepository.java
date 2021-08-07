package com.revature.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.models.User;


@Repository
//<<<<<<< HEAD
//public interface UserRepository extends JpaRepository<User, Integer>{
//
//=======
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value="select * from user where user_id=:userId", nativeQuery=true)
	User findUser(@Param("userId") int user_id);

	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
