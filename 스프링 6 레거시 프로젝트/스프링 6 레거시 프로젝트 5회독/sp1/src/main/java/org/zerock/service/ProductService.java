package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ProductDTO;
import org.zerock.mapper.ProductMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class ProductService {
	private final ProductMapper productMapper;

	@Autowired
	public ProductService(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	// 상품 등록
	public Integer register(ProductDTO productDTO) {
		productMapper.insert(productDTO);
		Integer pno = productDTO.getPno();
		productMapper.insertImages(productDTO);
		return pno;
	}
}
