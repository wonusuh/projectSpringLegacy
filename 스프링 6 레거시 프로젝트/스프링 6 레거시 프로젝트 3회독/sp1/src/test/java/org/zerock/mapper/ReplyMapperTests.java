package org.zerock.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.ReplyDTO;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class ReplyMapperTests {
    @Autowired
    private ReplyMapper replyMapper;

    @Test
    public void testInsert() {
	Long bno = 7148L;
	ReplyDTO replyDTO = ReplyDTO.builder().bno(bno).replyText("Reply ... ... ").replyer("user1").build();

	replyMapper.insert(replyDTO);
    }

    @Test
    public void testRead() {
	Long rno = 72L;
	log.info("-------------------------------");
	log.info(replyMapper.read(rno));
    }

    @Test
    public void testDelete() {
	Long rno = 72L;
	replyMapper.delete(rno);
    }

    @Test
    public void testUpdate() {
	Long rno = 72L;
	ReplyDTO replyDTO = ReplyDTO.builder().rno(rno).replyText("updated text").build();
	replyMapper.update(replyDTO);
    }

    @Test
    public void testInserts() {
	Long[] bnoList = { 7148L, 7147L };
	for (Long bno : bnoList) {
	    for (int i = 0; i < 10; i += 1) {
		ReplyDTO replyDTO = ReplyDTO.builder().bno(bno).replyer("replyer1").replyText("Sample Reply").build();
		replyMapper.insert(replyDTO);
	    } // end of 10 times loop
	} // end of bnoList
    }

    @Test
    public void testListOfBoard() {
	Long bno = 7148L;
	List<ReplyDTO> replyList = replyMapper.listOfBoard(bno, 0, 10);
	replyList.forEach((reply) -> {
	    log.info("\n" + reply);
	});
    }
}
