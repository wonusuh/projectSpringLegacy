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
    private ProductMapper productMapper;

    // 상품 등록
    public Integer register(ProductDTO productDTO) {
	productMapper.insert(productDTO);
	Integer pno = productDTO.getPno();
	productMapper.insertImages(productDTO);
	return pno;
    }

    // 상품목록 조회
    public ProductListPagingDTO getList(int page, int size) {
	// 페이지번호 최소값 고정
	page = (page <= 1) ? 1 : page;

	// 한 페이지에 보여줄 상품수 최대최소값 고정
	size = (size <= 10 || size >= 100) ? 10 : size;

	// 13페이지의 상품들을 보여주려면 120 개의 상품을 건너뛰기
	int skip = (page - 1) * size;

	// dto 리스트
	List<ProductListDTO> dtoList = productMapper.list(skip, size);

	// DB 전체 상품개수
	int totalCount = productMapper.listCount();

	return new ProductListPagingDTO(dtoList, totalCount, page, size);
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

	// 상품정보 수정
	productMapper.updateOne(productDTO);

	// 상품 이미지 갱신
	productMapper.insertImages(productDTO);
    }
}
