package com.abhay.demo.configrations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authProvider;  
	
	@Autowired
	private OldLoginInterceptor oldLoginInterceptor;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		
		//********orginal security**************
		 http.authorizeRequests()
         .antMatchers("/anonymous*").anonymous()
         .antMatchers("/**").permitAll()
         .anyRequest().authenticated()
          
         .and()
         .formLogin()
         .loginPage("/index")
         .successHandler(oldLoginInterceptor).failureHandler(customAuthenticationFailureHandler()).and()
         .rememberMe();

	}

	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception
	 {
	
	 auth.authenticationProvider(authProvider);
	 }

	 @Bean
	    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
	        return new CustomAuthenticationFailureHandler();
	    }



}