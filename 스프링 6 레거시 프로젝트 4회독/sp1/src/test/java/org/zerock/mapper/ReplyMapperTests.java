package org.zerock.mapper;

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
	Long bno = 7524L;

	ReplyDTO replyDTO = ReplyDTO.builder().bno(bno).replyText("댓글 내용").replyer("user1").build();

	replyMapper.insert(replyDTO);
    }

    @Test
    public void testRead() {
    }

    @Test
    public void testDelete() {
	Long rno = 167L;
	int deletedCount = replyMapper.delete(rno);
	log.info("---------------------------------");
	log.info("deletedCount : " + deletedCount);
    }

    // 댓글 수정 테스트
    @Test
    public void testUpdate() {
	Long rno = 167L;
	ReplyDTO replyDTO = ReplyDTO.builder().rno(rno).replyText("수정된 댓글내용").build();

	int updatedCount = replyMapper.update(replyDTO);
	log.info("---------------------------------");
	log.info("updatedCount : " + updatedCount);
    }
}
