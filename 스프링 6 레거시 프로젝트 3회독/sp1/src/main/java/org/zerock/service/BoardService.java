package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.BoardListPagingDTO;
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

    // 페이징
    public BoardListPagingDTO getList(int page, int size, String typeStr, String keyword) {
	// 페이지 번호가 0보다 작으면 무조건 1페이지
	page = page <= 0 ? 1 : page;

	// 사이즈가 10이하이거나 100보다 크면 10
	size = (size <= 10 || size > 100) ? 10 : size;

	// 2페이지라면 (2 - 1) * 10 이 되어야 함
	int skip = (page - 1) * size;

	//
	String[] types = typeStr != null ? typeStr.split("") : null;

	List<BoardDTO> list = boardMapper.listSearch(skip, size, types, keyword);

	int total = boardMapper.listCountSearch(types, keyword);

	return new BoardListPagingDTO(list, total, page, size, typeStr, keyword);
    }
}
