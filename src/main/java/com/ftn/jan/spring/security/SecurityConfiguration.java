package com.ftn.jan.spring.security;

import java.security.AuthProvider;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.ftn.jan.jpa.repository.UserRepository;
import com.ftn.jan.model.User;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	 private static String REALM="MY_TEST_REALM";
	 @Autowired
	 private CustomAuthenticationProvider authenticationProvider;
     
	    @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	       auth.authenticationProvider(authenticationProvider);

	    }
	     
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	  
	      http
	      	.csrf().disable()
	      	//.httpBasic();
	      	
//	      	.authorizeRequests()
//	        .antMatchers("/user/**")
//	        .hasAnyRole("SUBSCRIBER","ADMIN")
	        .httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
	        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
	    

	    	

	    }
	     
	    @Bean
	    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
	        return new CustomBasicAuthenticationEntryPoint();
	    }
	     
	    /* To allow Pre-flight [OPTIONS] request from browser */
	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	    }	
}
