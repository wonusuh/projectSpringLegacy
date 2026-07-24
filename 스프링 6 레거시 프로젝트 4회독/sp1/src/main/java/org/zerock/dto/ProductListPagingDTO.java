package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class ProductListPagingDTO {
    private List<ProductListDTO> productDTOList; // 상품 and 상품이미지 join 된 dto
    private int totalCount; // 조회된 전체 row 수
    private int page; // 현재 선택된 페이지
    private int size; // 한 페이지에 보여줄 상품의 개수
    private int start; // 페이지바에 보여지는 첫 번째 페이지번호
    private int end; // 페이지바에 보여지는 마지막 페이지번호
    private boolean prev; // 이전 페이지바 버튼 활성화 여부
    private boolean next; // 다음 페이지바 버튼 활성화 여부
    private List<Integer> pageNums; // 페이지바에 보여줄 숫자들 (페이지 번호들)

    // 생성자에서 페이지 계산
    public ProductListPagingDTO(List<ProductListDTO> productDTOList, int totalCount, int page, int size) {
	this.productDTOList = productDTOList;
	this.totalCount = totalCount;
	this.page = page;
	this.size = size;

	// 13 페이지를 보고있다면 페이지바의 끝 번호는 20
	int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

	// 끝번호가 20 이면 시작번호는 11
	this.start = tempEnd - 9;

	// 첫 번째 번호가 1이면 이전버튼 비활성화
	this.prev = this.start == 1 ? false : true;

	// 20 * 10 > 150
	if ((tempEnd * size) > totalCount) {
	    // ceil(150 / 10) -> 16
	    this.end = (int) (Math.ceil(totalCount / (double) size));
	} else {
	    // 20
	    this.end = tempEnd;
	}

	// 150 > (16 * 10) ?
	this.next = totalCount > (this.end * size) ? true : false;

	// 페이지바에 보여줄 페이지 번호들
	this.pageNums = IntStream.rangeClosed(this.start, this.end).boxed().toList();
    }
}
