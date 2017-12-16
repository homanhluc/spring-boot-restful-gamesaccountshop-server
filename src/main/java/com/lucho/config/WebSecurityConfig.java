package com.lucho.config;

import com.lucho.filtes.JWTAuthenticationFilter;
import com.lucho.filtes.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// Web Security

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
	
    // Authentication and Authorization
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		//h2 database console
		http.headers().frameOptions().disable();

		http.exceptionHandling()
				.and().anonymous()
				.and().servletApi()
				.and().headers().cacheControl();
		http.addFilterBefore(
				new JWTLoginFilter("/login", authenticationManager()),
				UsernamePasswordAuthenticationFilter.class);

		http.addFilterBefore(
				new JWTAuthenticationFilter(),
				UsernamePasswordAuthenticationFilter.class);



		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/apiX/**").authenticated()
				.antMatchers(HttpMethod.GET, "/login").permitAll();
    }
}
