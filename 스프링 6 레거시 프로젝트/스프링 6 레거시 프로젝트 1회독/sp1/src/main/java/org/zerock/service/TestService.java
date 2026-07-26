package org.zerock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.TestMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class TestService {

	private final TestMapper testMapper;

	public void insertAll(String str) {
		int resultA = testMapper.insertA(str);
		log.info("resultA: " + resultA);
		int resultB = testMapper.insertB(str);
		log.info("resultB: " + resultB);
	} // end insertAll
}
