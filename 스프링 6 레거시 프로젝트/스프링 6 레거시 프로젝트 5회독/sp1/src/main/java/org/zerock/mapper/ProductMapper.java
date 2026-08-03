package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.ProductDTO;

public interface ProductMapper {
	// 상품 등록
	int insert(ProductDTO productDTO);

	// 상품이미지 등록
	int insertImages(ProductDTO productDTO);

	// 상품 조회
	ProductDTO selectOne(@Param("pno") Integer pno);

	// 상품 삭제
	int deleteOne(@Param("pno") Integer pno);
}
