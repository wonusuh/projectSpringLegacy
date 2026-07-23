package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.ProductDTO;
import org.zerock.dto.ProductListDTO;

public interface ProductMapper {
  // 상품 등록
  int insert(ProductDTO productDTO);

  // 상품에 이미지 등록
  int insertImages(ProductDTO productDTO);

  // 상품 and 이미지 조회
  ProductDTO selectOne(@Param("pno") Integer pno);

  // 상품 삭제
  int deleteOne(@Param("pno") Integer pno);

  // 특정 상품의 이미지 삭제
  int deleteImages(@Param("pno") Integer pno);

  // 상품 수정
  int updateOne(ProductDTO productDto);

  // 상품 목록 조회
  List<ProductListDTO> list(@Param("skip") int skip, @Param("count") int count);

  // 상품 페이징 계산을 위한 상품 개수
  int listCount();
}
