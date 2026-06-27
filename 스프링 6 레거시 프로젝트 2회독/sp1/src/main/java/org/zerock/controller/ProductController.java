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

    // 나중에 서비스 주입이 필요함

    @GetMapping("register")
    public void registerGET() {
	log.info("product register");
    }

    @PostMapping("register")
    public String register(ProductDTO productDTO, @RequestParam("files") MultipartFile[] files,
	    RedirectAttributes reAtr) {

	log.info("----------");

	log.info(productDTO);
	log.info(files);

	return "redirect:/product/list";
    }
}
