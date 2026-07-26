package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.ProductDTO;
import org.zerock.dto.ProductListDTO;

public interface ProductMapper {
	int insert(ProductDTO productDTO);

	int insertImages(ProductDTO productDTO);

	ProductDTO selectOne(@Param("pno") Integer pno);

	int deleteOne(@Param("pno") Integer pno);

	int deleteImages(@Param("pno") Integer pno);

	int updateOne(ProductDTO productDTO);

	List<ProductListDTO> list(@Param("skip") int skip, @Param("count") int count);

	int listCount();
}
