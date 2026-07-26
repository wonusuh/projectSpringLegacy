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

//	@Test
//	public void testInsert() {
//		// 게시물의 번호를 결정 - DB 에 존재하는지 먼저 확인할 것
//		Long bno = 16369L;
//
//		// 새로운 댓글 생성
//		// Builder 를 이용하면 좀 더 간단
//		ReplyDTO replyDTO = ReplyDTO.builder().bno(bno).replyText("Reply..........").replyer("user1").build();
//
//		replyMapper.insert(replyDTO);
//	}

//	@Test
//	public void testRead() {
//		Long rno = 1L;
//
//		ReplyDTO replyDTO = replyMapper.read(rno);
//
//		log.info("read------------------------------------------------------------------------------");
//		log.info(replyDTO);
//	}

//	@Test
//	public void testDelete() {
//		Long rno = 1L;
//		log.info("DELETE ------------------------------------------------------------------------------");
//		log.info("DELETE rno : " + replyMapper.delete(rno));
//	}

//	@Test
//	public void testUpdate() {
//		ReplyDTO replyDTO = ReplyDTO.builder().rno(1L).replyText("Update Reply...............").build();
//		replyMapper.update(replyDTO);
//	}

//	@Test
//	public void testInserts() {
//		Long[] bnos = { 16369L, 6642L, 6641L };
//		for (Long bno : bnos) {
//			for (int i = 0; i < 10; i++) {
//				ReplyDTO replyDTO = ReplyDTO.builder().bno(bno).replyer("replyer1").replyText("Sample Reply.......")
//						.build();
//				replyMapper.insert(replyDTO);
//			}
//		}
//	}

//	@Test
//	public void testListOfBoard() {
//		Long bno = 16369L;
//		List<ReplyDTO> replyList = replyMapper.listOfBoard(bno, 0, 10);
//		replyList.forEach((reply) -> {
//			log.info(reply);
//		});
//	}
}
