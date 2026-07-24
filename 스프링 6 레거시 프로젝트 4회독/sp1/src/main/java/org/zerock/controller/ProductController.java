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
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.ProductDTO;
import org.zerock.dto.ProductListPagingDTO;
import org.zerock.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    @Autowired
    private ProductService productService;

    // 상품 등록화면 호출
    @GetMapping("/register")
    public void registerGET() {
	log.info("===== product register GET =====");
    }

    // 상품 등록서비스 호출
    @PostMapping("/register")
    public String register(ProductDTO productDto, @RequestParam("files") MultipartFile[] files,
	    RedirectAttributes redirectAttributes) {
	log.info("===== product register POST =====");
	log.info(productDto);
	log.info(files);

	// upload file
	List<String> uploadNames = this.uploadFiles(files);
	uploadNames.stream().forEach((eachUploadName) -> {
	    String uuid = eachUploadName.substring(0, 36);
	    String fileName = eachUploadName.substring(37);

	    log.info("\nuuid : " + uuid);
	    log.info("fileName : " + fileName + "\n");

	    productDto.addImage(uuid, fileName);
	});

	Integer pno = productService.register(productDto);
	redirectAttributes.addFlashAttribute("product", pno);

	return "redirect:/product/list";
    }

    // 서버 내 파일 업로드 처리
    private List<String> uploadFiles(MultipartFile[] files) throws RuntimeException {
	List<String> uploadNames = new ArrayList<>();

	// 방어 로직
	if (files == null || files.length == 0) {
	    return uploadNames;
	}

	String uploadPath = "C:\\upload";

	log.info("===== uploadPath =====");
	log.info(uploadPath);

	for (MultipartFile file : files) {
	    // 방어 로직
	    if (file.isEmpty()) {
		continue;
	    }

	    String fileName = file.getOriginalFilename();
	    String uploadName = UUID.randomUUID().toString() + "_" + fileName;

	    File targetFile = null;
	    targetFile = new File(uploadPath, uploadName);

	    try (InputStream inputStream = file.getInputStream();
		    OutputStream outputStream = new FileOutputStream(targetFile);) {
		log.info(targetFile.getAbsolutePath());
		FileCopyUtils.copy(inputStream, outputStream);
		uploadNames.add(uploadName);
	    } catch (Exception e) {
		log.error(e.getMessage());
		throw new RuntimeException(e.getMessage());
	    } finally {
	    }

	    // 이미지 파일이라면 썸네일을 생성
	    if (file.getContentType().startsWith("image")) {
		try {
		    Thumbnails.of(targetFile).size(200, 200).toFile(new File(uploadPath, "s_" + uploadName));
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		}
	    }
	} // end of loop

	return uploadNames;
    }

//    // 이미지 삭제
//    private void deleteFiles(List<String> fileNames) {
//	try {
//	    File uploadPath = new File("C:\\upload");
//	    for (String fileName : fileNames) {
//		File targetFile = new File(uploadPath, fileName);
//
//		// 파일 삭제
//		targetFile.delete();
//
//		// 썸네일 삭제
//		File targetThumbnail = new File(uploadPath, "s_" + fileName);
//		targetThumbnail.delete();
//	    }
//	} catch (Exception e) {
//	    //
//	} finally {
//	    //
//	}
//    }

    // 상품목록 조회
    @GetMapping("/list")
    public void list(@RequestParam(name = "page", defaultValue = "1") int page,
	    @RequestParam(name = "size", defaultValue = "10") int size, Model model) {
	ProductListPagingDTO productListPagingDTO = productService.getList(page, size);
	model.addAttribute("dto", productListPagingDTO);
    }

    // 상품 조회
    @GetMapping("/read/{pno}")
    public String read(@PathVariable("pno") Integer pno, Model model) {
	log.info("pno : " + pno);
	model.addAttribute("product", productService.read(pno));
	return "/product/read";
    }

    // 상품 수정화면 호출
    @GetMapping("/modify/{pno}")
    public String modify(@PathVariable("pno") Integer pno, Model model) {
	log.info("pno : " + pno);
	model.addAttribute("product", productService.read(pno));
	return "/product/modify";
    }

    // 상품 삭제
    @PostMapping("/remove")
    public String remove(@RequestParam("pno") Integer pno, RedirectAttributes redirectAttributes) {
	productService.remove(pno);
	redirectAttributes.addFlashAttribute("result", "deleted");
	return "redirect:/product/list";
    }

    // 상품 수정서비스 호출
    @PostMapping("/modify")
    public String modifyPOST(ProductDTO productDTO, @RequestParam("oldImages") String[] oldImages,
	    @RequestParam("files") MultipartFile[] files) {
	List<String> newFileNames = this.uploadFiles(files);

	if (oldImages != null && oldImages.length > 0) {
	    for (String oldImage : oldImages) {
		String uuid = oldImage.substring(0, 36);
		String fileName = oldImage.substring(37);
		productDTO.addImage(uuid, fileName);
	    } // end of for
	}

	if (newFileNames != null && newFileNames.size() > 0) {
	    for (String newImage : newFileNames) {
		String uuid = newImage.substring(0, 36);
		String fileName = newImage.substring(37);
		productDTO.addImage(uuid, fileName);
	    } // end of for
	}

	productService.modify(productDTO);

	return "redirect:/product/read/" + productDTO.getPno();
    }
}
