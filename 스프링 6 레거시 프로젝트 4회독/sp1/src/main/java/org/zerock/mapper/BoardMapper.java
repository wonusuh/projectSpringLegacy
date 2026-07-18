package org.zerock.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.BoardDTO;

public interface BoardMapper {
    int insert(BoardDTO boardDTO);

    BoardDTO selectOne(Long bno);

    int remove(Long bno);

    int update(BoardDTO boardDTO);

    ArrayList<BoardDTO> list();

    List<BoardDTO> list2(@Param("skip") int skip, @Param("count") int count);

    int listCount();

    // 게시물 검색
    List<BoardDTO> listSearch(@Param("skip") int skip, @Param("count") int count, @Param("types") String[] types,
	    @Param("keyword") String keyword);

    int listCountSearch(@Param("types") String[] types, @Param("keyword") String keyword);
}
