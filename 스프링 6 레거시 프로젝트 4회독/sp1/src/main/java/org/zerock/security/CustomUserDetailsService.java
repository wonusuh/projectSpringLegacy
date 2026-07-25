package org.zerock.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		log.info("========== loadUserByUsername ==========" + username);

		UserDetails user = User.builder().username(username).password(
				"$2a$10$.pZfR4dWXa9J93wClaNtrOsiDFMSOUyJ3gyyDgxbHVbsITiqwZEIC")
				.roles("USER").build();

		return user;
	}
}
