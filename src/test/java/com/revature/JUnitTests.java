package com.revature;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.exceptions.ResourceNotFoundException;

import com.revature.security.JwtUtils;


public class JUnitTests {

	JwtUtils jwt = new JwtUtils();
	

	
	@Test //needed in order for this to be recognized as a testing method
	public void testInvalidLogin() throws ResourceNotFoundException {
		System.out.println("TESTING INVALID LOGIN...");
			assertFalse("Token is invalid",jwt.validateJwtToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJidW5ueUBnbWFpbC5jb20iLCJpYXQiOjE2MjkwNzg1NjEsImV4cCI6MTYyOTE2NDk2MX0.u8-eNXSutAgyp-BPOdPmTu3s12_8880mQrlylUZmWeIEC9hEDVfPPalzv7wh1AbtOUBzNJezg-PPAV4CJ_li4Q"));

	}
	
	
	@Test //needed in order for this to be recognized as a testing method
	public void testValidLogin() throws ResourceNotFoundException {
		System.out.println("TESTING VALID LOGIN...");
			assertTrue(jwt.validateJwtToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJidW5ueUBnbWFpbC5jb20iLCJpYXQiOjE2MjkwNzg1NjEsImV4cCI6MTYyOTE2NDk2MX0.u8-eNXSutAgyp-BPOdPmTu3s12_8880mQrlylUZmWeIEC9hEDVfPPalzv7wh1AbtOUBzNJezg-PPAV4CJ_li4Q"));
	}
	
	
}//class end