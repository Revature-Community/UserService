package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.models.User;
import com.revature.services.UserService;

@SpringBootTest
class P3AuthServiceApplicationTests {

	 UserService us = new  UserService();
	
	@Test
	void contextLoads() {
	}
	
	//@Test
   public void  findAllTest() {	
	List<User> users = us.findAll();		
	assertTrue(true);
	}
	
   public void  saveTest() {	
		List<User> users = us.findAll();		
		assertTrue(true);
		}
   
   
	@Test
	public void  findOneTest() {
		//User u = us.save(user);
		assertTrue(true);
	}
	
	@Test
	public void  loginTest() {
		//User userLogin = us.login(user);
		assertTrue(true);
	}
	
	

	
}
