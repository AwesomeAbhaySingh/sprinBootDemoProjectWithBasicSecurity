package com.abhay.demo.configrations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.abhay.demo.model.User;
import com.abhay.demo.service.DefaultTransactionalService;



@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private DefaultTransactionalService defaultTransactionalService;
	


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = null;
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
     
		User userInfo=defaultTransactionalService.getUserInfoByMobileAndStatus(name,true);
		

		if (Objects.isNull(userInfo)) {
			return token;
		}
		if (StringUtils.isEmpty(userInfo.getAuthKey())) {
			logger.error("Authentication failed for user = " + name);
			SecurityContextHolder.getContext().setAuthentication(token);
			return token;
		}
		UserDetails userDetails = null;

		if (!StringUtils.isEmpty(userInfo.getAuthKey())) {
			Integer access = 0;
			userDetails = new org.springframework.security.core.userdetails.User(userInfo.getAuthKey(), password,
					true, true, true, true, getAuthorities(access));
		}
		if (Objects.isNull(userDetails)) {
			return null;
		}
		
		token = new UsernamePasswordAuthenticationToken(userInfo.getAuthKey(), password,
				userDetails.getAuthorities());

		
		SecurityContextHolder.getContext().setAuthentication(token);

		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

	private Collection<GrantedAuthority> getAuthorities(Integer access) { // TODO
		
		List<GrantedAuthority> authList = new ArrayList<>(2);

		if (access.compareTo(1) == 0) {
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			authList.add(new SimpleGrantedAuthority("USER"));
		}
		return authList;
	}

}