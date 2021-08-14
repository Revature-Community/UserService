# User & Role related services

### Purpose of this project

* Convert the existing monolith program into a microservice
* Our team is responsible for making User , Role and Authentication related logics
* Spring JWT and security will be implemented in our new micro-service

## Implement Role tables via Hibernate 

### @JoinTable provides a way in hiberante to generate many to many tables

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

### Resulted Diagram

[Schema Diagram](https://github.com/Revature-Community/UserService/blob/master/doc/p3diagram.png)

### Spring Security

* Spring Security has comprehensive and extensible support for both Authentication and Authorization
* Protection against attacks like session fixation, clickjacking, cross site request forgery, etc

#### Related files

* UserDetail interface convert our model objects and turn them into Authentication object 
* UserDetailService is our service  where we will inject the User Repository to obtain User object and use it to construct our UserDetail Authentication object 

#### In WebSecurityConfig, we define our authorized user with the following method. 
    @Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

### Simple implementation of JWT 

#### In WebSecurityConfig, after we add a jwt filter and UsernamePasswordAuthenticationFilter

* The request should comes in with a JWT token along with User informations 
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class;   
* By calling doFilterInternal() method, the program will parse the token using built-in spring jwt parser and check if the token exist. After making checking the token matches our "secret" which we define in property file ( or other secure place ),  it will check if the user exist.

#### Base JWT payload comunicatation between frontend and backend.

    client => payload( username, email, password) => our service
    client <= payload( JWT , type: BEAR, user information ) <= our service
    client => payload( JWT token, type: BEAR, other requests ) => our service

### Modification of buildUserForAuthentication method of UserDetailsServiceImpl.java 

* Our application wants to accept email as the authenication in place of username 
		
		private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
	        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	    }

* The change uses email to build userdetails users, so we can pass email instead of username later in the login request handlers 

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

* For incoming request to work, we need to generate the JWT token with email instead as well (in JwtUtils.java)

		return Jwts.builder()
				.setSubject((userPrincipal.getEmail()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
		


### Continue implementations

* implement the request payload class. The frontend client will need to send back the JWT token back to our service and our request handlers need to parse the body of requests into a request payload object first in order to use these data


* Reconfigure the WebSecurityConfig to block url requests

Aug 11 2021
 
