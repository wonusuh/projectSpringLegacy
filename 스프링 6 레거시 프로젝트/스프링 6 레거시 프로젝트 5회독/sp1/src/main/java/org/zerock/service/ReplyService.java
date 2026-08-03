package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ReplyDTO;
import org.zerock.dto.ReplyListPagingDTO;
import org.zerock.mapper.ReplyMapper;
import org.zerock.service.exception.ReplyException;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class ReplyService {
	private final ReplyMapper replyMapper;

	@Autowired
	public ReplyService(ReplyMapper replyMapper) {
		this.replyMapper = replyMapper;
	}

	// 댓글 등록
	public void add(ReplyDTO replyDTO) {
		try {
			replyMapper.insert(replyDTO);
		} catch (Exception e) {
			throw new ReplyException(500, "INSERT ERROR");
		}
	}

	// 댓글 조회
	public ReplyDTO getOne(Long rno) {
		try {
			return replyMapper.read(rno);
		} catch (Exception e) {
			throw new ReplyException(404, "NOT FOUND");
		}
	}

	// 댓글 수정
	public void modify(ReplyDTO replyDTO) {
		try {
			int count = replyMapper.update(replyDTO);

			// 예외
			if (count == 0) {
				throw new ReplyException(404, "NOT FOUND");
			}
		} catch (Exception e) {
			throw new ReplyException(500, "UPDATE ERROR");
		}
	}

	// 댓글 삭제
	public void remove(Long rno) {
		try {
			int count = replyMapper.delete(rno);

			// 예외
			if (count == 0) {
				throw new ReplyException(404, "NOT FOUND");
			}
		} catch (Exception e) {
			throw new ReplyException(500, "DELETE ERROR");
		}
	}

	// 댓글 목록 조회
	public ReplyListPagingDTO listOfBoard(Long bno, int page, int size) {
		try {
			// 3번 페이지를 로드하려면 20개의 댓글을 skip
			int skip = (page - 1) * size;

			// dto 리스트 조회
			List<ReplyDTO> dtoList = replyMapper.listOfBoard(bno, skip, size);

			// 해당 게시물의 전체 댓글수
			int count = replyMapper.countOfBoard(bno);

			// 페이징 계산
			return new ReplyListPagingDTO(dtoList, count, page, size);
		} catch (Exception e) {
			throw new ReplyException(500, e.getMessage());
		}
	}
}
