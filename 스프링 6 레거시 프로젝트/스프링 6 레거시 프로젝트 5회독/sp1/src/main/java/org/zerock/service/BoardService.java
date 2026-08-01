package org.zerock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.BoardListPagingDTO;
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

	// 게시물 목록 조회 v. 1
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

//	// 게시물 목록 조회 v. 2
//	public BoardListPagingDTO getList(int page, int size) {
//		// 페이지 번호 정합성
//		page = page <= 1 ? 1 : page;
//
//		// 한 페이지에 보여줄 dto 정합성
//		size = (size <= 10 || size >= 100) ? 10 : size;
//
//		// 3 페이지면 20개 건너뛰기
//		int skip = (page - 1) * size;
//
//		// dto 리스트
//		List<BoardDTO> dtoList = (ArrayList<BoardDTO>) boardMapper.list2(skip, size);
//
//		// DB 전체 dto 개수
//		int totalCount = boardMapper.listCount();
//
//		// 생성자에서 계산
//		return new BoardListPagingDTO(dtoList, totalCount, page, size);
//	}

	// 게시물 목록 조회 v. 3
	public BoardListPagingDTO getList(int page, int size, String typesStr, String keyword) {
		// 페이지 번호 정합성
		page = page <= 1 ? 1 : page;

		// 한 페이지에 보여줄 dto 정합성
		size = (size <= 10 || size >= 100) ? 10 : size;

		// 3 페이지면 20개 건너뛰기
		int skip = (page - 1) * size;

		// 검색유형 null 처리
		String[] types = {};
		if (typesStr == null || "".equals(typesStr.trim())) {
			types = null;
		} else {
			types = typesStr.split("");
		}

		// 검색어 null 처리
		if (keyword != null && "".equals(keyword.trim())) {
			keyword = null;
		}

		// dto 리스트
		List<BoardDTO> dtoList = (ArrayList<BoardDTO>) boardMapper.listSearch(skip, size, types, keyword);

		// DB 전체 dto 개수
		int totalCount = boardMapper.listCountSearch(types, keyword);

		// 생성자에서 계산
		return new BoardListPagingDTO(dtoList, totalCount, page, size, typesStr, keyword);
	}
}
