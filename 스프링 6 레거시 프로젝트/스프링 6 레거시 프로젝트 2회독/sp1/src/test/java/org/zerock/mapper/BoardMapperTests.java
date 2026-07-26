package org.zerock.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.BoardDTO;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src//main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardMapperTests {
    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testInsert() {
	BoardDTO boardDTO = BoardDTO.builder().title("title").content("content").writer("user00").build();
	int insertCount = boardMapper.insert(boardDTO);
	log.info("----------------------------------");
	log.info("insertCount : " + insertCount);
    }

    @Test
    public void testInser2() {
	BoardDTO boardDTO = BoardDTO.builder().title("title").content("content").writer("user00").build();
	int insertCount = boardMapper.insert(boardDTO);
	log.info("----------------------------------");
	log.info("insertCount : " + insertCount);
	log.info("==================================");
	log.info("BNO : " + boardDTO.getBno());

    }

    @Test
    public void testSelectOne() {
	BoardDTO board = boardMapper.selectOne(2L);
	log.info("board : " + board);
    }

    @Test
    public void testRemove() {
	int removeCount = boardMapper.remove(2L);
	log.info("----------------------------");
	log.info("removeCount : " + removeCount);
    }

    @Test
    public void testUpdate() {
	BoardDTO boardDto = BoardDTO.builder().bno(2L).title("Updated Title").content("Updated Content").delFlag(false)
		.build();
	int updateCount = boardMapper.update(boardDto);
	log.info("------------------------");
	log.info("updateCount : " + updateCount);
    }

    @Test
    public void testList() {
	List<BoardDTO> dtoList = boardMapper.list();
	log.info("dtoList");
	log.info(dtoList);
    }

    @Test
    public void testList2() {
	int page = 2;

	// 계산
	int skip = (page - 1) * 10;
	int count = 10;

	List<BoardDTO> dtoList = boardMapper.list2(skip, count);
	dtoList.stream().forEach((BoardDTO dto) -> {
	    log.info(dto);
	});
    }

    @Test
    public void testSearch() {
	int page = 2;

// 계산
	int skip = (page - 2) * 10;
	int count = 10;

	String[] types = new String[] { 
//		"T", //
//		"C", //
//		"W" //
	};
	String keyword = "Test";

	boardMapper.listSearch(skip, count, types, keyword);
    }
}
