package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class ProductListPagingDTO {
	private List<ProductListDTO> productDTOList;
	private int totalCount;
	private int page, size;
	private int start, end;
	private boolean prev, next;
	private List<Integer> pageNums;

	public ProductListPagingDTO(List<ProductListDTO> productDTOList, int totalCount, int page, int size) {
		this.productDTOList = productDTOList;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;

		// start 계산을 위한 end 페이지
		int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;
		this.start = tempEnd - 9;
		this.prev = start != 1; // start 값이 1이 아니라면 이전 페이지버튼 활성화

		// 임시 end 값 * size가 totalCount 보다 크다면 totalCount로 다시 계산 필요
		if ((tempEnd * size) > totalCount) {
			this.end = (int) (Math.ceil((totalCount / (double) size)));
		} else {
			this.end = tempEnd;
		}

		// end 값 * size 보다 totalCount 가 크다면 next로 이동 가능
		this.next = totalCount > (this.end * size);

		// 화면에 출력한 번호들 계산
		this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
	}
}
