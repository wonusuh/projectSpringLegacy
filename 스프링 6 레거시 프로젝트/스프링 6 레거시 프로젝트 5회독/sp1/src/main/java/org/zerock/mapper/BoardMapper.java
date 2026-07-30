package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.BoardDTO;

public interface BoardMapper {
	// 게시물 등록
	int insert(BoardDTO boardDTO);

	// 게시물 조회
	BoardDTO selectOne(Long bno);

	// 게시물 삭제
	int remove(@Param("bno") Long bno);

	// 게시물 수정
	int update(BoardDTO boardDTO);

	// 게시물 목록 조회
	List<BoardDTO> list();
}
