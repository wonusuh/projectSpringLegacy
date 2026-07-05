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
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardMapperTests {
    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testInsert() {
	BoardDTO boardDTO = BoardDTO.builder().title("title").content("content").writer("user00").build();

	int insertCount = boardMapper.insert(boardDTO);
	log.info("----------");
	log.info("insertCount : " + insertCount);
    }

    @Test
    public void testInsert2() {
	BoardDTO boardDTO = BoardDTO.builder().title("title").content("content").writer("user00").build();

	int insertCount = boardMapper.insert(boardDTO);
	log.info("----------");
	log.info("insertCount : " + insertCount);
	log.info("==========");
	log.info("BNO : " + boardDTO.getBno());
    }

    @Test
    public void testSelectOne() {
	Long bno = 6646L;
	BoardDTO boardDTO = boardMapper.selectOne(bno);
	log.info("board : " + boardDTO);
    }

    @Test
    public void testRemove() {
	Long bno = 6646L;
	int removeCount = boardMapper.remove(bno);
	log.info("------------------------------------------");
	log.info("removeCount : " + removeCount);
    }

    @Test
    public void testUpdate() {
	BoardDTO boardDTO = BoardDTO.builder().bno(6646L).title("Update Title").content("Update Content").delFlag(false)
		.build();

	int updateCount = boardMapper.update(boardDTO);

	log.info("--------------------------------");
	log.info("updateCount : " + updateCount);
    }

    @Test
    public void testList() {
	List<BoardDTO> dtoList = boardMapper.list();
	log.info("dtoList");
	log.info(dtoList);

	dtoList.stream().forEach((eachDto) -> {
	    log.info(eachDto);
	});
    }

    @Test
    public void testList2() {
	int page = 2;

	// 계산
	int skip = (page - 1) * 10;
	int count = 10;

	List<BoardDTO> dtoList = boardMapper.list2(skip, count);

	dtoList.stream().forEach((eachDto) -> {
	    log.info("\n" + eachDto + "\n");
	});
    }
}
