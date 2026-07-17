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
}
