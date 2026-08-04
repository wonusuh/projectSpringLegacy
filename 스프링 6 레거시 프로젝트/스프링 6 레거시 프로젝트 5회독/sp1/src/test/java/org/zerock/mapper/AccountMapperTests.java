package org.zerock.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.AccountDTO;
import org.zerock.dto.AccountRole;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class AccountMapperTests {
	private final PasswordEncoder passwordEncoder;
	private final AccountMapper accountMapper;

	@Autowired
	public AccountMapperTests(PasswordEncoder passwordEncoder, AccountMapper accountMapper) {
		this.passwordEncoder = passwordEncoder;
		this.accountMapper = accountMapper;
	}

	@Test
	public void testSelectOne() {
		String uid = "user100";
		AccountDTO accountDTO = accountMapper.selectOne(uid);
		log.info(accountDTO);
		log.info(accountDTO.getRoleNames());
	}

	@Test
	@Transactional
	@Commit
	public void testInsert() {
		for (int i = 1; i <= 100; i++) {
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setUid("user" + i);
			accountDTO.setUpw(passwordEncoder.encode("1111"));
			accountDTO.setUname("User" + i);
			accountDTO.setEmail("user" + i + "@aaa.com");
			accountDTO.addRole(AccountRole.USER);

			if (i >= 80) {
				accountDTO.addRole(AccountRole.MANAGER);
			}

			if (i >= 90) {
				accountDTO.addRole(AccountRole.ADMIN);
			}

			accountMapper.insert(accountDTO);
			accountMapper.insertRoles(accountDTO);
		} // end of for
	}

	@Test
	public void testEncoding() {
		String pw = "1111";
		String enPw = passwordEncoder.encode(pw);
		log.info(enPw);
		log.info("--------");
		boolean match = passwordEncoder.matches(pw, enPw);
		log.info(match);
	}
}
