package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class BoardListPagingDTO {
	private List<BoardDTO> boardDTOList; // dto 리스트
	private int totalCount; // DB 에서 조회된 전체 board 의 개수
	private int page; // 현재 보고있는 페이지번호
	private int size; // 한 페이지에 보여줄 board 의 개수
	private int start; // 페이지바의 첫 번째 페이지번호
	private int end; // 페이지바의 마지막 페이지번호
	private boolean prev; // 이전 페이지바 버튼 활성화 여부
	private boolean next; // 다음 페이지바 버튼 활성화 여부
	private List<Integer> pageNums; // 페이지바에 표기할 페이지번호들
	private String types; // 검색 유형
	private String keyword; // 검색어

	// 생성자에서 페이징 계산
	public BoardListPagingDTO(List<BoardDTO> boardDTOList, int totalCount, int page, int size, String types,
			String keyword) {
		this.boardDTOList = boardDTOList;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;
		this.types = types;
		this.keyword = keyword;

		// ceil(13/10.0) * 10 = 20
		int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

		// 20 - 9 = 11
		this.start = tempEnd - 9;

		// 시작페이지번호가 1이 아닌 경우에만 활성화
		this.prev = start != 1;

		// 끝 페이지번호 재계산 200 > 133
		if ((tempEnd * size) > totalCount) {
			this.end = (int) Math.ceil(totalCount / (double) size); // 14
		} else {
			this.end = tempEnd; // 20
		}

		// 다음 페이지바 이동버튼 활성화 여부
		this.next = totalCount > (this.end * size) ? true : false;

		// 페이지바에 보여줄 번호들
		this.pageNums = IntStream.rangeClosed(this.start, this.end).boxed().toList();
	}
}
