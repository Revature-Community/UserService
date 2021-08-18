package com.revature.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.Role;
import com.revature.models.RoleTitle;
import com.revature.models.User;
import com.revature.payloads.JwtResponse;
import com.revature.payloads.signRequest;
import com.revature.repositories.RoleRepository;
import com.revature.repositories.UserRepository;
import com.revature.security.JwtUtils;
import com.revature.security.UserDetailsImpl;
import com.revature.services.UserService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/users")
public class UserController {	
	//This is Authentication manager
	AuthenticationManager authenticationManager;

	UserRepository userRepository;

	RoleRepository roleRepository;

	PasswordEncoder encoder;

	JwtUtils jwtUtils; 

	UserService userServ;
	
	
	@Autowired
	private UserController(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils, UserService userServ ) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
		this.userServ = userServ;

	}

	@GetMapping(path = "/", produces = "application/json")
	public List<User> getAllUsers() {
			// JwtRequest 
		return userServ.findAll();

	}
	
	//@CrossOrigin(origins = "http://localhost:8089")   
	@GetMapping(path="/{id}",produces="application/json")
	public User getUserById(@PathVariable(value="id") Long id) throws ResourceNotFoundException {
		//try { User user = userServ.findOne(id) catch(ResourceNotFoundException e) { }
		
		return userServ.findOne(id);
	}


	@PostMapping(path="/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) throws ResourceNotFoundException{
		System.out.println("data " + user.getEmail());
		
		// - JwtAuthTokenFilter (extends OncePerRequestFilter) pre-processes HTTP request,
		//from Token, create Authentication and populate it to SecurityContext
		// - UsernamePasswordAuthenticationToken gets username/password from 
		//login Request and combines into an instance of Authentication interface
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		
		
		//SecurityContextHolder is the most fundamental object where  
		//details of the present security context of the application is store 
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		// create a JWT token
		String jwt = jwtUtils.generateJwtToken(authentication);
		System.out.println("token: "+jwt);
		//Authentication object. This principal can be cast into a UserDetails object to lookup the username, password 
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();	
		
		// A GrantedAuthority is an authority that is granted to the principal. Such authorities are usually ‘roles’
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		System.out.println("roles: "+ roles);
		 JwtResponse j = new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 roles);
		 
		 System.out.println("response: "+ j);
		return ResponseEntity.ok(new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 roles));
	//	User userLoggedIn = new User();
		//return ResponseEntity.status(HttpStatus.ACCEPTED).body(userLoggedIn ); 
	}
	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity< User> createNewUser(@RequestBody  signRequest newRequest ) { // User userToAdd
		System.out.println(newRequest.getRoles());
		if (userRepository.existsByEmail(newRequest.getEmail())) {
			System.out.println("user exists \n");
			return ResponseEntity
					.badRequest()
					.build(); // replace it with a jwt response
					
		}
		//may need to refactor the code above 
		//Look at the findbyUsername function in the userrepository class and implement that 
		
		// **Another way to check if a username already exists below.. may need to implement this in the future 
		//Authentication authentication = authenticationManager.authenticate(
		//new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));**
//		
//		userToAdd.setRole(RoleTitle.User);
		
		User user = new User();
		user.setEmail(newRequest.getEmail());
		user.setFirstName(newRequest.getFirstName());
		user.setLastName(newRequest.getLastName());
		user.setUsername(newRequest.getUsername());

		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		String encodedPwd = passwordEncoder.encode(newRequest.getPassword());
		user.setPassword(encodedPwd);

		
		
		// always  User role for now 
		Role role = new Role();
		RoleTitle title = RoleTitle.User;
		role.setTitle(title);
		 Set<Role> roleSet = new HashSet<>();
		 user.setRoles(roleSet);	// now it is a set<Role> with only 1 role :)
			
		// save data
		User userSaved = userServ.save(user);  // now save the user with data from the frontend and Role of 'User'
		//For third table,  create user_roles repository /dao to save the incoming data :  6 (user id from userSaved) , 1 (role id of user) 
		
		
		
		// constructd JWT payload 
		
		 // create JWT token
		// return JWT response  
		
		return ResponseEntity.ok().body(userSaved); // we can instead send back  the JWT payload constructing from the userSaved later
	}

//	@DeleteMapping("/remove/{name}")
//	public Map<String, Boolean> removeUser(@PathVariable("email") String email) throws ResourceNotFoundException {
//		return userServ.remove(email);
//
//	}

}