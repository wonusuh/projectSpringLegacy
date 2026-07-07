package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.ProductDTO;

public interface ProductMapper {
    int insert(ProductDTO productDTO);

    int insertImages(ProductDTO productDTO);

    ProductDTO selectOne(@Param("pno") Integer pno);

    int deleteOne(@Param("pno") Integer pno);

    int deleteImages(@Param("pno") Integer pno);
}
