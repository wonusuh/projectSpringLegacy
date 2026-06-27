package org.zerock.mapper;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ProductDTO;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class ProductMapperTests {
    @Autowired
    private ProductMapper productMapper;

    @Transactional
    @Commit
    @Test
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

    @Test
    public void testSelectOne() {
	Integer pno = 1;
	ProductDTO dto = productMapper.selectOne(pno);

	log.info(dto);
	dto.getImageList().forEach((eachImage) -> {
	    log.info("=====\n" + eachImage);
	});
    }

    @Transactional
    @Commit
    @Test
    public void testUpdateOne() {
	ProductDTO productDTO = ProductDTO.builder().pno(1).pname("Updated Product").pdesc("updated description")
		.price(6000).build();

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

    @Transactional
    @Commit
    @Test
    public void testInsertDummies() {
	for (int i = 0; i < 45; i += 1) {
	    ProductDTO productDTO = ProductDTO.builder().pname("Product " + i).pdesc("Product Desc " + i)
		    .writer("user" + (i % 10)).price(i * (int) 100).build();

	    // insert into tbl_product
	    productMapper.insert(productDTO);

	    productDTO.addImage(UUID.randomUUID().toString(), i + "_test_1.jpg");
	    productDTO.addImage(UUID.randomUUID().toString(), i + "_test__2.jpg");

	    log.info("----------");
	    log.info(productDTO.getImageList());

	    // insert into tbl_product_image
	    productMapper.insertImages(productDTO);
	} // end of for
    }

    @Test
    public void testList() {
	productMapper.list(0, 10).forEach((eachProduct) -> {
	    log.info("\n=====" + eachProduct.toString());
	});

	log.info(productMapper.listCount());
    }
}
