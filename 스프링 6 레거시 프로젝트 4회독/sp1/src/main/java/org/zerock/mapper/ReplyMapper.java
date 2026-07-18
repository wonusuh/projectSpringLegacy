package org.zerock.mapper;

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
}
