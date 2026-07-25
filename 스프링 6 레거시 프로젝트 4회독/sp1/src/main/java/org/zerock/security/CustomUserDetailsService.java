package org.zerock.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.dto.AccountDTO;
import org.zerock.mapper.AccountMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {
	private final AccountMapper accountMapper;

	@Autowired
	public CustomUserDetailsService(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("========== loadUserByUsername ==========" + username);
		AccountDTO accountDto = accountMapper.selectOne(username);

		// 방어 로직
		if (accountDto == null) {
			throw new UsernameNotFoundException("Account Not Found");
		}

		return accountDto;
	}
}
