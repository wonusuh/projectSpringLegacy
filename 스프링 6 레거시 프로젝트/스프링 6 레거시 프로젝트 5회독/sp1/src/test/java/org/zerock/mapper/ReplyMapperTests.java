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
	private final ReplyMapper replyMapper;

	@Autowired
	public ReplyMapperTests(ReplyMapper replyMapper) {
		this.replyMapper = replyMapper;
	}

	@Test
	public void testInsert() {
		Long bno = 7900L;
		ReplyDTO replyDTO = ReplyDTO.builder().bno(bno).replyText("댓글 내용").replyer("user1").build();
		replyMapper.insert(replyDTO);
	}

	@Test
	public void testRead() {
		Long rno = 1L;
		log.info("========== testRead ==========");
		log.info(replyMapper.read(rno));
	}

	// 댓글 삭제
	@Test
	public void testDelete() {
		Long rno = 1L;
		log.info("========== testDelete ==========");
		log.info(replyMapper.delete(rno));
	}

	// 댓글 수정
	@Test
	public void testUpdate() {
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setRno(1L);
		replyDTO.setReplyText("수정된 댓글 내용");

		log.info("========== testUpdate ==========");
		log.info(replyMapper.update(replyDTO));
	}

	// 더미 댓글 추가
	@Test
	public void testInserts() {
		Long[] bnoList = { 7900L, 7899L, 7898L };

		for (Long eachBno : bnoList) {
			for (int i = 0; i < 10; i += 1) {
				ReplyDTO replyDTO = ReplyDTO.builder().bno(eachBno).replyer("테스트 댓글 작성자" + i).replyText("테스트 댓글 내용" + i)
						.build();
				replyMapper.insert(replyDTO);
			} // end of for
		} // end of for
	}

	// 테스트 댓글목록 조회
	@Test
	public void testListOfBoard() {
		Long bno = 7900L;
		List<ReplyDTO> dtos = replyMapper.listOfBoard(bno, 0, 10);
		dtos.stream().forEach((dto) -> {
			log.info(dto.toString() + "\n");
		});
	}
}
