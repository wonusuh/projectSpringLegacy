package org.zerock.mapper;

import java.util.ArrayList;

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
}
