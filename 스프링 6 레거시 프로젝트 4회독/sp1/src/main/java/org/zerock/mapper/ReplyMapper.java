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

  // 특정 게시물의 댓글수
  int getNumberOfRepliesByBno(@Param("bno") Long bno);
}
