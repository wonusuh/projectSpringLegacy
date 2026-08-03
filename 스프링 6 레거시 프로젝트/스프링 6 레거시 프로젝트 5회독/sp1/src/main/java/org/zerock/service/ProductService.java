package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ProductDTO;
import org.zerock.dto.ProductListDTO;
import org.zerock.dto.ProductListPagingDTO;
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

	// 상품목록 조회
	public ProductListPagingDTO getList(int page, int size) {
		// 페이지 번호가 0보다 작으면 무조건 1페이지
		page = page <= 1 ? 1 : page;
		// 사이즈가 10보다 작거나 100보다 크면 10
		size = (size <= 10 || size >= 101) ? 10 : size;
		// 3페이지를 로드하려면 20개 건너뛰기
		int skip = (page - 1) * size;
		// dto 조회
		List<ProductListDTO> list = productMapper.list(skip, size);
		// 페이징 계산용 전체상품 개수
		int total = productMapper.listCount();
		// 페이징
		return new ProductListPagingDTO(list, total, page, size);
	}

	// 상품 조회
	public ProductDTO read(Integer pno) {
		return productMapper.selectOne(pno);
	}

	// 상품 삭제
	public void remove(Integer pno) {
		productMapper.deleteOne(pno);
	}

	// 상품 수정
	public void modify(ProductDTO productDTO) {
		// 기존 이미지 삭제
		productMapper.deleteImages(productDTO.getPno());

		// 상품 정보 수정
		productMapper.updateOne(productDTO);

		// 상품 이미지 갱신
		productMapper.insertImages(productDTO);
	}
}
