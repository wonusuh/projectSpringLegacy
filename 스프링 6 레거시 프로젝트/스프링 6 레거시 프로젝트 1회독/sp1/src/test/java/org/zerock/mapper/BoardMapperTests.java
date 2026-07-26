package org.zerock.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardMapperTests {

	@Autowired
	private BoardMapper boardMapper;

//	@Test
//	public void testInsert() {
//		BoardDTO boardDTO = BoardDTO.builder().title("title").content("content").writer("user00").build();
//		long insertCount = boardMapper.insert(boardDTO);
//		log.info("-------------------------------");
//		log.info("insert count: " + insertCount);
//		log.info("-------------------------------");
//		log.info("BNO : " + boardDTO.getBno());
//	}
//
//	@Test
//	public void testSelectOne() {
//		Long bno = 2L;
//		BoardDTO board = boardMapper.selectOne(bno);
//		log.info("board : " + board);
//	}
//
//	@Test
//	public void testRemove() {
//		Long bno = 2L;
//		int removeCount = boardMapper.remove(bno);
//		log.info("delete count : " + removeCount);
//	}
//
//	@Test
//	public void testUpdate() {
//		BoardDTO boardDTO = BoardDTO.builder().bno(2L).title("updated title").content("updated content").delFlag(false)
//				.build();
//		int updateCount = boardMapper.update(boardDTO);
//		log.info("---------------------------------------");
//		log.info("update count : " + updateCount);
//	}
//
//	@Test
//	public void testList() {
//		List<BoardDTO> dtoList = boardMapper.list();
//		log.info("-------------------------------dtoList-------------------------------");
//		log.info(dtoList);
//		dtoList.stream().forEach((dto) -> {
//			log.info(dto);
//		});
//	}

//	@Test
//	public void testList2() {
//		int page = 2;
//
//		// 계산
//		int skip = (page - 1) * 10;
//		int count = 10;
//
//		List<BoardDTO> dtoList = boardMapper.list2(skip, count);
//		dtoList.stream().forEach((dto) -> {
//			log.info(dto);
//		});
//	}

	@Test
	public void testSearch() {
		int page = 2;

		// 계산
		int skip = (page - 1) * 10;
		int count = 10;

		String[] types = new String[] { "T", "C", "W" };
//		String keyword = "Test";
		String keyword = null;

		boardMapper.listSearch(skip, count, types, keyword);
	}
}
