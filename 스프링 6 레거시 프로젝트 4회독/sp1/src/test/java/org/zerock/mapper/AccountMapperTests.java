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
	public void testEncoding() {
		String pw = "1111";
		String enPw = passwordEncoder.encode(pw);
		log.info("\n" + enPw + "\n");
		log.info("==========");
		boolean isMatched = passwordEncoder.matches(pw, enPw);
		log.info(isMatched);
	}

	@Test
	@Transactional
	@Commit
	public void testInsert() {
		for (int i = 1; i <= 100; i += 1) {
			AccountDTO dto = new AccountDTO();
			dto.setUid("user" + i);
			dto.setUpw(passwordEncoder.encode("1111"));
			dto.setUname("User" + i);
			dto.setEmail("user" + i + "@aaa.com");
			dto.addRole(AccountRole.USER);

			if (i >= 80) {
				dto.addRole(AccountRole.MANAGER);
			}

			if (i >= 90) {
				dto.addRole(AccountRole.ADMIN);
			}

			accountMapper.insert(dto);
			accountMapper.insertRoles(dto);
		} // end of for
	}

	@Test
	public void testSelectOne() {
		String uid = "user100";
		AccountDTO dto = accountMapper.selectOne(uid);
		log.info(dto);
		log.info(dto.getRoleNames());
	}
}
