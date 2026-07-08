package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ProductDTO;
import org.zerock.dto.ProductListDTO;
import org.zerock.dto.ProductListPagingDTO;
import org.zerock.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ProductService {
    @Autowired
    private final ProductMapper productMapper;

    // 상품 등록
    public Integer register(ProductDTO productDTO) {
	productMapper.insert(productDTO);
	Integer pno = productDTO.getPno();
	productMapper.insertImages(productDTO);
	return pno;
    }

    // 상품 목록과 페이징
    public ProductListPagingDTO getList(int page, int size) {
	// 첫 번째 페이지가 1이하이면 1로 고정
	page = page <= 1 ? 1 : page;

	// 사이즈 범위 고정
	size = (size <= 10 || size > 100) ? 10 : size;

	// OFFSET
	int skip = (page - 1) * size;

	// 상품목록
	List<ProductListDTO> list = productMapper.list(skip, size);

	// 전체 상품개수
	int totalCount = productMapper.listCount();

	return new ProductListPagingDTO(list, totalCount, page, size);
    }
}
