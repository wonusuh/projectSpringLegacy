package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ProductDTO;
import org.zerock.dto.ProductListDTO;
import org.zerock.dto.ProductListPagingDTO;
import org.zerock.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class ProductService {
	private final ProductMapper productMapper;

	public Integer register(ProductDTO productDTO) {
		productMapper.insert(productDTO);
		Integer pno = productDTO.getPno();
		productMapper.insertImages(productDTO);
		return pno;
	}

	public ProductListPagingDTO getList(int page, int size) {
		// 페이지 번호가 0 이하이면 무조건 1페이지
		page = page <= 0 ? 1 : page;
		// 사이즈가 10이하이거나 100보다 크면
		size = (size <= 10 || page > 100) ? 10 : size;

		int skip = (page - 1) * size;
		List<ProductListDTO> list = productMapper.list(skip, size);
		int total = productMapper.listCount();
		return new ProductListPagingDTO(list, total, page, size);
	}

	public ProductDTO read(Integer pno) {
		return productMapper.selectOne(pno);
	}

	public void remove(Integer pno) {
		productMapper.deleteOne(pno);
	}

	public void modify(ProductDTO productDTO) {
		productMapper.deleteImages(productDTO.getPno()); // 기존이미지 삭제
		productMapper.updateOne(productDTO); // 상품정보 수정
		productMapper.insertImages(productDTO); // 상품이미지 갱신
	}
}
