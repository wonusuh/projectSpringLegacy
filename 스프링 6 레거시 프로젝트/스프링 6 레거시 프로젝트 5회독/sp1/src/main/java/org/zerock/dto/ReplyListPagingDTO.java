package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class ReplyListPagingDTO {
	private List<ReplyDTO> replyDTOList; // dto 리스트
	private int totalCount; // 특정 게시물의 전체댓글수
	private int page, size; // 로드할 페이지, 한 페이지에 로드할 댓글의 개수
	private int start, end; // 페이지바의 첫 번째, 끝 번호
	private boolean prev, next; // 이전, 이후 버튼 활성화 여부
	private List<Integer> pageNums; // 페이지바에 출력할 페이지번호들

	// 생성자에서 페이징 계산
	public ReplyListPagingDTO(List<ReplyDTO> replyDTOList, int totalCount, int page, int size) {
		this.replyDTOList = replyDTOList;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;

		// start계산을 위한 end 페이지
		int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

		// 20 - 9 = 11
		this.start = tempEnd - 9;

		// start값이 1이 아니라면 이전 페이지로 이동 필요
		this.prev = start != 1;

		// 임시 end 값 * size가 totalCount 보다 크다면 totalCount로 다시 계산 필요
		if ((tempEnd * size) > totalCount) {
			// 200 > 133 -> 14
			this.end = (int) (Math.ceil(totalCount / (double) size));
		} else {
			// 20
			this.end = tempEnd;
		}

		// end 값 * size 보다 totalCount가 크다면 next로 이동 가능
		this.next = totalCount > (this.end * size);

		// 화면에 출력한 번호들 계산
		this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
	}
}
