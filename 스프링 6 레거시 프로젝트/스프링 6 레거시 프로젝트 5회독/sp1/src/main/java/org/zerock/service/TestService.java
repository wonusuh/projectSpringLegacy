package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.TestMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class TestService {
	private final TestMapper testMapper;

	@Autowired
	public TestService(TestMapper testMapper) {
		this.testMapper = testMapper;
	}

	// 트랜잭션 테스트
	public void insertAll(String str) {
		log.info("result A : " + testMapper.insertA(str));
		log.info("result B : " + testMapper.insertB(str));
	}
}
