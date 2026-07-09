package org.zerock.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.dto.AccountDTO;
import org.zerock.dto.AccountRole;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	log.info("========== loadUserByUsername ==========" + username);

	AccountDTO accountDTO = new AccountDTO();

	accountDTO.setUid(username);
	accountDTO.setUpw("$2a$10$wV90L6K/PFXVDbQrVEHMGO46zIDc5i/QJIFcZG4IAkvE1qo6CGHGG");
	accountDTO.addRole(AccountRole.USER);
	accountDTO.addRole(AccountRole.MANAGER);

	return accountDTO;
    }
}
