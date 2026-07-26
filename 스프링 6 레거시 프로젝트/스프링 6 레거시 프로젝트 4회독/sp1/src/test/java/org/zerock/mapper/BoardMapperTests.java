package org.zerock.mapper;

import java.util.ArrayList;
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
	log.info("insert Count : " + insertCount);
    }

    @Test
    public void testInsert2() {
	BoardDTO boardDTO = BoardDTO.builder().title("title").content("content").writer("user00").build();

	int insertCount = boardMapper.insert(boardDTO);
	log.info("----------");
	log.info("insert Count : " + insertCount);
	log.info("==========");
	log.info("BNO : " + boardDTO.getBno());
    }

    @Test
    public void testSelectOne() {
	Long bno = 7151L;
	BoardDTO boardDTO = boardMapper.selectOne(bno);
	log.info("board : " + boardDTO);
    }

    @Test
    public void testRemove() {
	Long bno = 7150L;
	int removeCount = boardMapper.remove(bno);
	log.info("removeCount : " + removeCount);
    }

    @Test
    public void testUpdate() {
	BoardDTO boardDTO = BoardDTO.builder().bno(7150L).title("updated title").content("updated content")
		.delFlag(false).build();

	int numberOfUpdated = boardMapper.update(boardDTO);
	log.info("-------------------------");
	log.info("number of updated : " + numberOfUpdated);
    }

    @Test
    public void testList() {
	ArrayList<BoardDTO> boardDTOList = boardMapper.list();
	log.info("-------------------------");
	boardDTOList.stream().forEach((boardDTO) -> {
	    log.info(boardDTO);
	});
    }

    @Test
    public void testList2() {
	int page = 2;

	// 계산
	int skip = (page - 1) * 10;
	int count = 10;

	List<BoardDTO> boardDTOList = boardMapper.list2(skip, count);
	boardDTOList.stream().forEach((boardDto) -> {
	    log.info("\n" + boardDto + "\n");
	});
    }

    @Test
    public void testSearch() {
	int page = 2;

	// 계산
	int skip = (page - 1) * 10;
	int count = 10;

	String[] types = new String[] { "T", "C", "W" };
	String keyword = "테스트 키워드";

	boardMapper.listSearch(skip, count, types, keyword);
    }
}
