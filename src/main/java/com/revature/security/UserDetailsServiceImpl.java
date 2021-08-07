package com.revature.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repositories.UserRepository;

/*
 * security.core.userdetails.UserDetailsService class
 * This class takes user data and construct a UserDetailService Object which 
 * can be used in authenticationManagerBuilder later
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	UserRepository userRepository;
	
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		
		
		return UserDetailsImpl.build(user);

	}
	
	 
	    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
	        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	    }
	    
	    /**
		   * * Deprcated *
		   * This method generates an user with USER/EMPLOYER permission
		   * @param email string that represents the email of the account
		   * @return security user with authorities
		   */
	    private List<GrantedAuthority> getUserAuthority(Set<String> userRoles) {
	        Set<GrantedAuthority> roles = new HashSet<>();
	        userRoles.forEach((role) -> {
	            roles.add(new SimpleGrantedAuthority(role));
	        });

	        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	        return grantedAuthorities;
	    }
}
