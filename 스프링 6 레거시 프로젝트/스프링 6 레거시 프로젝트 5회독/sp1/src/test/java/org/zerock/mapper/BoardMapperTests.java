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
	private final BoardMapper boardMapper;

	@Autowired
	public BoardMapperTests(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Test
	public void testInsert() {
		BoardDTO boardDTO = BoardDTO.builder().title("title").content("content").writer("user00").build();

		int insertCount = boardMapper.insert(boardDTO);

		log.info("--------------------");
		log.info("insertCount : {}", insertCount);

		log.info("====================");
		log.info("abcdABCD");
		log.info("한글한글");
		log.info("BNO : {}", boardDTO.getBno());
	}

	@Test
	public void testSelectOne() {
		Long bno = 7527L;
		BoardDTO boardDTO = boardMapper.selectOne(bno);
		log.info("====================");
		log.info("BoardDTO : {}", boardDTO.toString());
	}

	@Test
	public void testRemove() {
		Long bno = 7527L;
		int removeCount = boardMapper.remove(bno);
		log.info("====================");
		log.info("removed count : {}", removeCount);
	}

	@Test
	public void testUpdate() {
		Long bno = 7527L;
		BoardDTO boardDTO = BoardDTO.builder().bno(bno).title("updated title").content("updated content").delFlag(false)
				.build();

		int updatedCount = boardMapper.update(boardDTO);
		log.info("====================");
		log.info("updatedCount : {}", updatedCount);
	}

	@Test
	public void testList() {
		List<BoardDTO> boardDTOList = boardMapper.list();
		log.info("====================");
		log.info("boardDTOList");
		log.info(boardDTOList);

		boardDTOList.stream().forEach((eachBoardDTO) -> {
			log.info(eachBoardDTO + "\n");
		});
	}

	@Test
	public void testList2() {
		int page = 2; // 현재 페이지

		// 계산
		int skip = (page - 1) * 10; // 2 페이지면 10개를 스킵
		int count = 10; // 스킵 한 곳으로부터 10개 출력

		List<BoardDTO> boardDTOList = boardMapper.list2(skip, count);

		boardDTOList.stream().forEach((eachBoardDTO) -> {
			log.info(eachBoardDTO + "\n");
		});
	}

	@Test
	public void testSearch() {
		int page = 2; // 조회할 페이지번호
		int count = 10; // 한 페이지에 보여줄 게시물의 개수
		int skip = (page - 1) * count;
//		String[] types = { "T", "C", "W" };
		String[] types = { "W" };
		String keyword = "";

		// 빈 배열은 null 처리
		if (types.length == 0) {
			types = null;
		}

		// 빈 문자열은 null 처리
		if ("".equals(keyword)) {
			keyword = null;
		}

		boardMapper.listSearch(skip, count, types, keyword);
	}
}
