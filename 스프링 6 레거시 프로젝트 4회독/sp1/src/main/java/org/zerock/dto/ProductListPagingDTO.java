package org.zerock.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductListPagingDTO {
    private List<ProductListDTO> productDTOList; // 상품 and 상품이미지 join 된 dto
    private int totalCount; // 조회된 전체 row 수
    private int page; // 현재 선택된 페이지
    private int size; // 한 페이지에 보여줄 상품의 개수
}
