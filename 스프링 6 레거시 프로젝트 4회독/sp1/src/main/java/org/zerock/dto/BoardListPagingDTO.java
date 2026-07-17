package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class BoardListPagingDTO {
    private List<BoardDTO> boardDTOList; // 게시물 dto 리스트
    private int totalCount; // DB 에 있는 게시물의 개수
    private int page; // 현재 보고있는 페이지
    private int size; // 한 페이지에 보여줄 게시물의 개수
    private int start; // 현재 페이지바에 보여지고있는 첫 번째 페이지 번호
    private int end; // 현재 페이지바에 보여지고있는 마지막 페이지 번호
    private boolean prev; // 이전 페이지바 활성화 여부
    private boolean next; // 다음 페이지바 활성화 여부
    private List<Integer> pageNums; // 페이지바에 보여줄 페이지 번호들

    // 생성자에서 페이징을 연산
    public BoardListPagingDTO(List<BoardDTO> boardDTOList, int totalCount, int page, int size) {
	this.boardDTOList = boardDTOList;
	this.totalCount = totalCount;
	this.page = page;
	this.size = size;

	// 현재 보고있는 페이지로부터 페이지바의 끝번호를 계산
	int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

	// 끝번호 - 9 = 시작번호
	this.start = tempEnd - 9;

	// 페이지바의 시작번호가 1이면 이전페이지로 버튼 비활성화
	this.prev = start == 1 ? false : true;

	// 20 * 10 > 135
	if ((tempEnd * size) > totalCount) {
	    // 끝번호를 재계산
	    this.end = (int) (Math.ceil(totalCount / (double) size)); // 14
	} else {
	    // 20 * 10 <= 135
	    this.end = tempEnd;
	}

	// 다음 페이지바 버튼 활성화여부
	this.next = totalCount > (this.end * size);

	// 페이지바에 보여줄 숫자들 리스트
	this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
    }
}
