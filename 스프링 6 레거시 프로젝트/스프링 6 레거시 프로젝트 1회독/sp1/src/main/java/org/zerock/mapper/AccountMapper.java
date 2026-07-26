package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.AccountDTO;

public interface AccountMapper {
	int insert(AccountDTO accountDTO);

	int insertRoles(AccountDTO accountDTO);

	AccountDTO selectOne(@Param("uid") String uid);
}
