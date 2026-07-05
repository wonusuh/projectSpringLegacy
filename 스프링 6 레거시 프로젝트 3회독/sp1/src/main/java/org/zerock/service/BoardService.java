package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.BoardDTO;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class BoardService {
    @Autowired
    private final BoardMapper boardMapper;

    // 목록 조회
    public List<BoardDTO> getList() {
	return boardMapper.list();
    }

    // 게시글 등록
    public Long register(BoardDTO dto) {
	int insertCount = boardMapper.insert(dto);
	log.info("insertCount : " + insertCount);
	return dto.getBno();
    }

    // 게시물 조회
    public BoardDTO read(Long bno) {
	return boardMapper.selectOne(bno);
    }

    // 게시물 삭제
    public void remove(Long bno) {
	boardMapper.remove(bno);
    }

    // 게시물 수정
    public void modify(BoardDTO boardDTO) {
	boardMapper.update(boardDTO);
    }
}
