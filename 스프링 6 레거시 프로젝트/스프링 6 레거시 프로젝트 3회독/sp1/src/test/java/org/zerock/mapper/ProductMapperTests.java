package org.zerock.mapper;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ProductDTO;
import org.zerock.dto.ProductListDTO;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class ProductMapperTests {
    @Autowired
    private ProductMapper productMapper;

    @Test
    @Transactional
    @Commit
    public void testInsert() {
	ProductDTO productDTO = ProductDTO.builder().pname("Product").pdesc("Product Desc").writer("user1").price(4000)
		.build();

	// insert into tbl_product
	productMapper.insert(productDTO);

	productDTO.addImage(UUID.randomUUID().toString(), "_test_1.jpg");
	productDTO.addImage(UUID.randomUUID().toString(), "_test__2.jpg");

	// insert into tbl_product_image
	productMapper.insertImages(productDTO);
    }

    /**
     * 상품, 이미지 조회 테스트
     */
    @Test
    public void testSelectOne() {
	Integer pno = 48;
	ProductDTO producDTO = productMapper.selectOne(pno);
	log.info("\n" + producDTO + "\n");
	producDTO.getImageList().forEach((img) -> {
	    log.info("\n" + img + "\n");
	});
    }

    // 상품 수정 테스트
    @Test
    @Transactional
    @Commit
    public void testUpdateOne() {
	ProductDTO productDTO = ProductDTO.builder().pno(48).pname("Updated Prod").pdesc("updated desc").price(6100)
		.build();

	productDTO.addImage(UUID.randomUUID().toString(), "test3.jpg");
	productDTO.addImage(UUID.randomUUID().toString(), "test4.jpg");
	productDTO.addImage(UUID.randomUUID().toString(), "test5.jpg");

	// 기존 이미지 삭제
	productMapper.deleteImages(productDTO.getPno());

	// 상품정보 수정
	productMapper.updateOne(productDTO);

	// 상품 이미지 갱신
	productMapper.insertImages(productDTO);
    }

    // 더미 상품데이터 생성
    @Test
    @Transactional
    @Commit
    public void testInsertDummies() {
	for (int i = 0; i < 45; i += 1) {
	    ProductDTO productDTO = ProductDTO.builder().pname("Product " + i).pdesc("Product Desc " + i)
		    .writer("uesr" + (i % 10)).price(4000).build();

	    // INSERT INTO tbl_product
	    productMapper.insert(productDTO);

	    productDTO.addImage(UUID.randomUUID().toString(), i + "_test_1.jpg");
	    productDTO.addImage(UUID.randomUUID().toString(), i + "_test_2.jpg");

	    log.info("\n-------------------------------------------------------------------------\n");

	    // INSERT INTO tbl_product_image
	    productMapper.insertImages(productDTO);
	} // end of for
    }

    // 상품목록 조회
    @Test
    public void testList() {
	List<ProductListDTO> productDtoList = productMapper.list(0, 10);
	productDtoList.stream().forEach((product) -> {
	    log.info("\n" + product.toString() + "\n");
	});

	log.info(productMapper.listCount());
    }
}
