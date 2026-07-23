package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.ProductDTO;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/product")
@Log4j2
public class ProductController {

  // 나중에 서비스 주입

  // 상품 등록화면 호출
  @GetMapping("/register")
  public void registerGET() {
    log.info("===== product register GET =====");
  }

  // 상품 등록서비스 호출
  @PostMapping("/register")
  public String register(ProductDTO produDto, @RequestParam("files") MultipartFile[] files,
      RedirectAttributes redirectAttributes) {
    log.info("===== product register POST =====");
    log.info(produDto);
    log.info(files);
    return "redirect:/product/list";
  }
}
