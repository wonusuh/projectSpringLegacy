package org.zerock.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.dto.AccountDTO;
import org.zerock.mapper.AccountMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private final AccountMapper accountMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("--------------------------loadUserByUsername---------------------------------- {}", username);
		AccountDTO accountDTO = accountMapper.selectOne(username);

		//
		if (accountDTO == null) {
			throw new UsernameNotFoundException(" Account Not Found ");
		}

		return accountDTO;
	}
}
