package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.ProductDTO;
import org.zerock.dto.ProductListPagingDTO;
import org.zerock.service.ProductService;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/product")
@Log4j2
public class ProductController {
    @Autowired
    private ProductService productService;

    // 상품 등록화면 호출
    @GetMapping("/register")
    public void registerGET() {
	log.info("------------------------------------------------");
	log.info("registerGET");
    }

    // 상품 등록
    @PostMapping("/register")
    public String register(ProductDTO productDTO, @RequestParam("files") MultipartFile[] files,
	    RedirectAttributes reAtr) {
	log.info("------------------------------------------------");
	log.info(productDTO);
	log.info(files);

	// upload file
	List<String> uploadNameList = this.uploadFiles(files);
	uploadNameList.forEach((eachName) -> {
	    String uuid = eachName.substring(0, 36);
	    String fileName = eachName.substring(37);
	    log.info("uuid : " + uuid);
	    log.info("fileName : " + fileName);
	    productDTO.addImage(uuid, fileName);
	});

	Integer pno = productService.register(productDTO);
	reAtr.addFlashAttribute("product", pno);

	return "redirect:/product/list";
    }

    // 파일 업로드
    private List<String> uploadFiles(MultipartFile[] files) throws RuntimeException {
	List<String> uploadNames = new ArrayList<>();

	// 방어 로직
	if (files == null || files.length == 0) {
	    return uploadNames;
	}

	String uploadPath = "C:\\upload";
	log.info("------------------------------------------------");
	log.info("uploadPath : " + uploadPath);

	// 요청받은 파일 배열을 순회
	for (MultipartFile file : files) {
	    // 방어 로직
	    if (file.isEmpty()) {
		continue;
	    }

	    String fileName = file.getOriginalFilename();
	    String uploadName = UUID.randomUUID().toString() + "_" + fileName;
	    File targetFile = new File(uploadPath, uploadName);

	    try (InputStream fin = file.getInputStream(); OutputStream fos = new FileOutputStream(targetFile);) {
		log.info("targetFile.getAbsolutePath() : " + targetFile.getAbsolutePath());

		FileCopyUtils.copy(fin, fos);
		uploadNames.add(uploadName);
	    } catch (Exception e) {
		log.error("e.getMessage() : " + e.getMessage());
		throw new RuntimeException(e.getMessage());
	    } finally {
	    }

	    // 이 파일이 이미지파일 이라면 썸네일을 생성
	    if (file.getContentType().startsWith("image")) {
		try {
		    Thumbnails.of(targetFile).size(200, 200).toFile(new File(uploadPath, "s_" + uploadName));
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		}
	    }
	} // end of files loop

	return uploadNames;
    }

    // 파일 삭제
    private void deleteFiles(List<String> fileNameList) {
	try {
	    File uploadPath = new File("C:\\upload");

	    for (String fileName : fileNameList) {
		File targetFile = new File(uploadPath, fileName);
		targetFile.delete();

		// 썸네일 삭제
		File targetThumb = new File(uploadPath, "s_" + fileName);
		targetThumb.delete();
	    }
	} catch (Exception e) {
	} finally {
	}
    }

    @GetMapping("/list")
    public void list(@RequestParam(name = "page", defaultValue = "1") int page,
	    @RequestParam(name = "size", defaultValue = "10") int size, org.springframework.ui.Model model) {
	ProductListPagingDTO dto = productService.getList(page, size);
	model.addAttribute("dto", dto);
    }
}
