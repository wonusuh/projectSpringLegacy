package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class ReplyListPagingDTO {
    private List<ReplyDTO> replyDTOList;
    private int totalCount; // 조회된 전체 댓글의 개수
    private int page; // 현재 선택된 페이지
    private int size; // 한 페이지에 보여줄 댓글의 개수
    private int start; // 페이지바에 보여지는 첫 번째 페이지
    private int end; // 페이지바에 보여지는 끝 페이지
    private boolean prev; // 이전 페이지바 활성화
    private boolean next; // 이후 페이지바 활성화
    private List<Integer> pageNums; // 페이지바에 보여줄 페이지번호들

    public ReplyListPagingDTO(List<ReplyDTO> replyDTOList, int totalCount, int page, int size) {
	this.replyDTOList = replyDTOList;
	this.totalCount = totalCount;
	this.page = page;
	this.size = size;

	// 임시 끝번호
	int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

	// 시작번호
	this.start = tempEnd - 9;

	// 이전 버튼 활성화
	this.prev = start != 1;

	// 끝번호
	if ((tempEnd * size) > totalCount) {
	    this.end = (int) (Math.ceil(totalCount / (double) size));
	} else {
	    this.end = tempEnd;
	}

	// 다음 페이지 활성화
	this.next = totalCount > (this.end * size);

	// 페이지바에 보여줄 페이지번호들
	this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
    }
}
