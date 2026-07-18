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
    private BoardMapper boardMapper;

    // 게시물 목록 조회
//    public List<BoardDTO> getList() {
//	return boardMapper.list();
//    }

    // 게시물 등록
    public Long register(BoardDTO boardDTO) {
	log.info("----- Service register -----");
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

    // 게시물 페이지네이션
    public BoardListPagingDTO getList(int page, int size, String typeStr, String keyword) {
	// 현재 페이지가 1보다 작으면 1로 고정
	page = page < 1 ? 1 : page;

	// 한 페이지에 보여줄 게시물의 갯수를 벗어나면 10개로 고정
	size = (size < 10 || size > 100) ? 10 : size;

	// 현재 2페이지를 보고있다면 10개를 건너뛰어야함
	int skip = (page - 1) * size;

	String[] types = typeStr == null ? null : typeStr.split("");
	List<BoardDTO> list = boardMapper.listSearch(skip, size, types, keyword);
	int total = boardMapper.listCountSearch(types, keyword);

	return new BoardListPagingDTO(list, total, page, size, typeStr, keyword);
    }
}
