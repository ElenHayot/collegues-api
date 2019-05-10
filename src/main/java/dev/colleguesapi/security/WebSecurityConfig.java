package dev.colleguesapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dev.colleguesapi.filter.JWTAuthorizationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;
	
	@Autowired
	JWTAuthorizationFilter jwtAuthFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.cors().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers(HttpMethod.GET, "/collegues").hasRole("USER")
			.antMatchers(HttpMethod.POST, "/collegues").hasRole("ADMIN")
			.antMatchers(HttpMethod.PATCH, "/collegues").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/me").hasRole("USER")
			
			.antMatchers("/auth").permitAll()
		
			.anyRequest().authenticated()
			.and().headers().frameOptions().disable()
			
			.and()
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
			
			.logout()
			.logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpStatus.OK.value()))
			.deleteCookies(TOKEN_COOKIE);
			
	}
	
}
