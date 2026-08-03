package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.ProductDTO;
import org.zerock.dto.ProductListDTO;

public interface ProductMapper {
	// 상품 등록
	int insert(ProductDTO productDTO);

	// 상품이미지 등록
	int insertImages(ProductDTO productDTO);

	// 상품 조회
	ProductDTO selectOne(@Param("pno") Integer pno);

	// 상품 삭제
	int deleteOne(@Param("pno") Integer pno);

	// 상품이미지 삭제
	int deleteImages(@Param("pno") Integer pno);

	// 상품 수정
	int updateOne(ProductDTO productDTO);

	// 상품목록 조회
	List<ProductListDTO> list(@Param("skip") int skip, @Param("count") int count);

	// 전체 상품개수 조회
	int listCount();
}
