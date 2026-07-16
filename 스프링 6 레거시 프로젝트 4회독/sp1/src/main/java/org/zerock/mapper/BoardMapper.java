package org.zerock.mapper;

import java.util.ArrayList;

import org.zerock.dto.BoardDTO;

public interface BoardMapper {
    int insert(BoardDTO boardDTO);

    BoardDTO selectOne(Long bno);

    int remove(Long bno);

    int update(BoardDTO boardDTO);

    ArrayList<BoardDTO> list();
}
