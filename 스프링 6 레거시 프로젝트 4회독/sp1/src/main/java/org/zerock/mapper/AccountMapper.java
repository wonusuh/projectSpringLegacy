package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.AccountDTO;

public interface AccountMapper {
	// 계정 등록
	int insert(AccountDTO accountDto);

	// 권한 등록
	int insertRoles(AccountDTO accountDto);

	// 계정 조회
	AccountDTO selectOne(@Param("uid") String uid);
}
