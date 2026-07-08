package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class ProductListPagingDTO {
    private List<ProductListDTO> productListDTO;
    private int totalCount; // 전체 상품수
    private int page; // 현재 선택된 페이지
    private int size; // 한 페이지에 보여줄 상품개수
    private int start; // 페이지바에 첫 번째로 표기되는 페이지번호
    private int end; // 페이지바에 마지막에 표기되는 페이지번호
    private boolean prev; // 이전 버튼
    private boolean next; // 이후 버튼
    private List<Integer> pageNums; // 페이지바에 보여줄 숫자들

    public ProductListPagingDTO(List<ProductListDTO> productListDTO, int totalCount, int page, int size) {
	this.productListDTO = productListDTO;
	this.totalCount = totalCount;
	this.page = page;
	this.size = size;

	// 마지막 페이지
	int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

	// 11 ~ 20
	this.start = tempEnd - 9;

	// 이전버튼
	this.prev = start == 1 ? false : true;

	// 끝번호 * 10 > 전체상품
	if ((tempEnd * size) > totalCount) {
	    // 전체상품개수 / 10, 반올림
	    this.end = (int) (Math.ceil(totalCount / (double) size));
	} else {
	    this.end = tempEnd;
	}

	// 끝번호 * 사이즈 보다 전체상품갯수가 더 많으면 이후버튼 활성화
	this.next = totalCount > (this.end * size);

	// 페이지바에 표기할 번호들
	this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
    }
}
