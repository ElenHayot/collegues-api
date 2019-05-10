package dev.colleguesapi.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.colleguesapi.entite.InfosAuthentification;
import dev.colleguesapi.service.CollegueService;
import io.jsonwebtoken.Jwts;

@RestController
public class AuthentificationCtrl {

	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN;
	
	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;
	
	@Value("${jwt.secret}")
	private String SECRET;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private CollegueService service;
	
	@PostMapping(value = "/auth")
	public ResponseEntity<?> authenticate(@RequestBody InfosAuthentification authRequest, HttpServletResponse response) {
	   
		UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());
		Authentication auth = authManager.authenticate(upaToken);
		
		User user = (User) auth.getPrincipal();
		String rolesList = user.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(","));
		
		Map<String, Object> infosSuppToken= new HashMap<>();
		infosSuppToken.put("roles", rolesList);
		
		String jetonJWT = Jwts.builder()
				.setSubject(user.getUsername())
				.addClaims(infosSuppToken)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET)
				.compact();
		
		Cookie authCookie = new Cookie(TOKEN_COOKIE, jetonJWT);
		authCookie.setHttpOnly(true);
		authCookie.setMaxAge(EXPIRES_IN * 1000);
		authCookie.setPath("/");
		response.addCookie(authCookie);
		
	    return ResponseEntity.ok().build();
	}
	
	
	@GetMapping(value = "/me")
	public ResponseEntity<?> meGetInfos() throws Exception {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		try{
			return ResponseEntity.ok(service.findCollegueByEmail(email));
		}catch(Exception e){ 
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}
	
}
