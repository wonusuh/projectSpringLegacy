package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.TestMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TestService {
    @Autowired
    private TestMapper testMapper;

    // 트랜잭션 테스트
    public void insertAll(String str) {
	int resultA = testMapper.insertA(str);
	log.info("insertA : " + resultA);
	int resultB = testMapper.insertB(str);
	log.info("insertB : " + resultB);
    }
}
