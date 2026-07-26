package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public class BoardListPagingDTO {
    private List<BoardDTO> boardDTOList;
    private int totalCount; // DB 내의 전체 개시글의 수
    private int page; // 현재 선택된(보고있는) 페이지의 번호
    private int size; // 한 페이지에 출력할 게시글의 수
    private int start; // 페이지바의 첫 번째 번호
    private int end; // 페이지바의 마지막 번호
    private boolean prev; // "이전" 버튼 활성화 여부
    private boolean next; // "이후" 버튼 활성화 여부
    private List<Integer> pageNums; // 페이지바에 출력할 번호들 리스트
    private String types; // 검색 종류 "T", "C", "W"
    private String keyword; // 검색어

    public BoardListPagingDTO(List<BoardDTO> boardDTOList, int totalCount, int page, int size, String types,
	    String keyword) {
	this.boardDTOList = boardDTOList;
	this.totalCount = totalCount;
	this.page = page;
	this.size = size;
	this.types = types;
	this.keyword = keyword;

	// start 계산을 위한 end 페이지
	int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

	// 끝번호 - 9 == 시작번호
	this.start = tempEnd - 9;

	// 시작번호가 1이 아니면 이전버튼 활성화
	this.prev = start != 1;

	// (끝번호 * 출력할 게시글수) > DB 게시글 수
	if ((tempEnd * size) > totalCount) {
	    // (20 * 10) > 150
	    this.end = (int) (Math.ceil(totalCount / (double) size));
	} else {
	    // (20 * 10) < 300
	    this.end = tempEnd;
	}

	// "이후" 버튼 활성화 여부
	this.next = totalCount > (this.end * size);

	// 페이지바의 숫자들 계산
	this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
    }
}
