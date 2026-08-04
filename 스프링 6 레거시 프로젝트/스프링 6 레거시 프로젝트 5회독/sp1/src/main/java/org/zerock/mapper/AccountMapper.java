package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.AccountDTO;

public interface AccountMapper {
	// 계정 저장
	int insert(AccountDTO accountDTO);

	// 권한 저장
	int insertRoles(AccountDTO accountDTO);

	// 계정 조회
	AccountDTO selectOne(@Param("uid") String uid);
}
