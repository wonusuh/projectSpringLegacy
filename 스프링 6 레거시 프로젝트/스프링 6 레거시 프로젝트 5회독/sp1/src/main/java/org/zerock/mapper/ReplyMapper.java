package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.ReplyDTO;

public interface ReplyMapper {
	// 댓글 등록
	int insert(ReplyDTO replyDTO);

	// 댓글 조회
	ReplyDTO read(@Param("rno") Long rno);

	// 댓글 삭제
	int delete(@Param("rno") Long rno);

	// 댓글 수정
	int update(ReplyDTO replyDTO);

	// 댓글 목록 조회
	List<ReplyDTO> listOfBoard(@Param("bno") Long bno, @Param("skip") int skip, @Param("limit") int limit);

	// 페이징을 위한 특정 게시물의 전체 댓글의 개수
	int countOfBoard(@Param("bno") Long bno);
}
