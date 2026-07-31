package org.zerock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.BoardDTO;
import org.zerock.mapper.BoardMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class BoardService {
	private final BoardMapper boardMapper;

	@Autowired
	public BoardService(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	// 게시물 목록 조회
	public List<BoardDTO> getList() {
		return (ArrayList<BoardDTO>) boardMapper.list();
	}

	// 게시물 등록
	public Long register(BoardDTO boardDTO) {
		int insertCount = boardMapper.insert(boardDTO);
		log.info("insertCount : " + insertCount);
		return boardDTO.getBno();
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
