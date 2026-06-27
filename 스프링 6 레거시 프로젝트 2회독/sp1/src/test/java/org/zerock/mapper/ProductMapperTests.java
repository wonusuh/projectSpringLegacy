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

	// isnert into tbl_product
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
}
