package dev.colleguesapi.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.repository.CollegueRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	CollegueRepository collegueRepo;
	
	public UserDetailsServiceImpl(CollegueRepository collegueRepo) {
		this.collegueRepo = collegueRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

		Collegue userFound = this.collegueRepo.findByEmail(userEmail)
				.orElseThrow(() -> new UsernameNotFoundException("Collegue not found"));
		
		return new User(userFound.getEmail(), userFound.getPassword(), userFound.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}
	
}
