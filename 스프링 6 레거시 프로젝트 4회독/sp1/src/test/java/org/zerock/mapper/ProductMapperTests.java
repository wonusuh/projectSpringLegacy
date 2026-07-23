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

  @Test
  @Transactional
  @Commit
  public void testInsert() {
    ProductDTO productDto = ProductDTO.builder().pname("Product").pdesc("Product Desc").writer("user1")
        .price((int) 3800L).build();

    // INSERT INTO tbl_product
    productMapper.insert(productDto);
    productDto.addImage(UUID.randomUUID().toString(), "_test_1.jpg");
    productDto.addImage(UUID.randomUUID().toString(), "_test_2.jpg");

    // INSET INTO tbl_product_image
    productMapper.insertImages(productDto);
  }

  @Test
  public void testSelectOne() {
    Integer pno = 96;
    ProductDTO productDto = productMapper.selectOne(pno);
    log.info(productDto.toString());
    productDto.getImageList().stream().forEach((image) -> {
      log.info("image : " + image.toString());
    });
  }

  @Test
  @Transactional
  @Commit
  public void testUpdateOne() {
    ProductDTO productDto = ProductDTO.builder().pno(96).pname("Updated pname").pdesc("Updated pdesc").price(4321)
        .build();

    productDto.addImage(UUID.randomUUID().toString(), "updated_test3.jpg");
    productDto.addImage(UUID.randomUUID().toString(), "updated_test4.jpg");
    productDto.addImage(UUID.randomUUID().toString(), "updated_test5.jpg");

    // 기존 이미지 삭제
    productMapper.deleteImages(productDto.getPno());

    // 상품 정보 수정
    productMapper.updateOne(productDto);

    // 상품 이미지 갱신
    productMapper.insertImages(productDto);
  }

  // 상품목록 더미 데이터
  @Test
  @Transactional
  @Commit
  public void testInsertDummies() {
    for (int i = 0; i < 45; i += 1) {
      ProductDTO productDto = ProductDTO.builder().pname("pName_" + i).pdesc("pDesc_" + i).writer("user" + (i % 10))
          .price(3800).build();

      // INSERT INTO tbl_product
      productMapper.insert(productDto);
      productDto.addImage(UUID.randomUUID().toString(), i + "_test1.jpg");
      productDto.addImage(UUID.randomUUID().toString(), i + "_test2.jpg");

      log.info("-------------------------------------------------------");
      log.info(productDto.getImageList());

      // INSERT INTO tbl_product_image
      productMapper.insertImages(productDto);
    } // end of for
  }

  @Test
  public void testList() {
    productMapper.list(0, 10).stream().forEach((eachProduct) -> {
      log.info(eachProduct.toString() + "\n");
    });

    log.info(productMapper.listCount());
  }
}
