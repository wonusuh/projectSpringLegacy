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

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/product")
@Log4j2
public class ProductController {
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	// 상품 등록화면 호출
	@GetMapping("/register")
	public String registerGET() {
		log.info("=== === === === === registerGET === === === === ===");
		return "/product/register.jsp";
	}

	// 상품 등록서비스 호출
	@PostMapping("/register")
	public String registerPOST(ProductDTO productDTO, @RequestParam("files") MultipartFile[] multipartFileArray,
			RedirectAttributes redirectAttributes) {
		log.info("=== === === === === registerPOST === === === === ===");
		log.info(productDTO);
		log.info(multipartFileArray);

		// 파일 업로드
		List<String> uploadNameList = uploadFiles(multipartFileArray);
		uploadNameList.stream().forEach((eachNm) -> {
			String uuid = eachNm.substring(0, 36);
			String fileName = eachNm.substring(37);
			log.info(uuid);
			log.info(fileName);
			productDTO.addImage(uuid, fileName);
		});

		Integer pno = productService.register(productDTO);
		redirectAttributes.addFlashAttribute("product", pno);

		return "redirect:/product/list";
	}

	// 상품목록 화면 호출
	@GetMapping("/list")
	public String list(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model) {
		ProductListPagingDTO dto = productService.getList(page, size);
		model.addAttribute("dto", dto);
		return "/product/list.jsp";
	}

	// 상품 조회화면 호출
	@GetMapping("/read/{pno}")
	public String read(@PathVariable("pno") Integer pno, Model model) {
		log.info("pno: " + pno);
		model.addAttribute("product", productService.read(pno));
		return "/product/read.jsp";
	}

	// 상품 수정화면 호출
	@GetMapping("/modify/{pno}")
	public String modifyGET(@PathVariable("pno") Integer pno, Model model) {
		log.info("pno: " + pno);
		model.addAttribute("product", productService.read(pno));
		return "/product/modify.jsp";
	}

	// 상품 수정서비스 호출
	@PostMapping("/modify")
	public String modifyPost(ProductDTO productDTO, @RequestParam("oldImages") String[] oldImages,
			@RequestParam("files") MultipartFile[] files) {
		List<String> newFileNames = uploadFiles(files);

		// oldImages
		if (oldImages != null && oldImages.length > 0) {
			for (String oldImage : oldImages) {
				String uuid = oldImage.substring(0, 36);
				String fileName = oldImage.substring(37);
				productDTO.addImage(uuid, fileName);
			}
		}

		if (newFileNames != null && newFileNames.size() > 0) {
			for (String newImage : newFileNames) {
				String uuid = newImage.substring(0, 36);
				String fileName = newImage.substring(37);
				productDTO.addImage(uuid, fileName);
			}
		}

		productService.modify(productDTO);
		return "redirect:/product/read/" + productDTO.getPno();
	}

	// 상품 삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("pno") Integer pno, RedirectAttributes rttr) {
		productService.remove(pno);
		rttr.addFlashAttribute("result", "deleted");
		return "redirect:/product/list";
	}

	// 서버 내 파일 업로드
	private List<String> uploadFiles(MultipartFile[] multipartFileArray) throws RuntimeException {
		List<String> uploadNameList = new ArrayList<>();

		// 방어로직
		if (multipartFileArray == null || multipartFileArray.length == 0) {
			return uploadNameList;
		}

		String uploadPath = "C:\\upload";
		log.info("=== === === === === uploadPath === === === === ===");
		log.info(uploadPath);

		// 멀티파트파일 배열을 순회
		for (MultipartFile eachMultipartFile : multipartFileArray) {
			// 방어로직
			if (eachMultipartFile.isEmpty()) {
				continue;
			}

			String fileName = eachMultipartFile.getOriginalFilename();
			String uploadName = UUID.randomUUID().toString() + "_" + fileName;
			File targetFile = new File(uploadPath, uploadName);

			try (InputStream inputStream = eachMultipartFile.getInputStream();
					OutputStream outputStream = new FileOutputStream(targetFile);) {
				log.info(targetFile.getAbsolutePath());
				FileCopyUtils.copy(inputStream, outputStream);
				uploadNameList.add(uploadName);
			} catch (Exception e) {
				log.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			} finally {
				log.info("===");
			}

			// 업로드에 성공한 파일이 이미지파일이면 썸네일도 생성
			if (eachMultipartFile.getContentType().startsWith("image")) {
				try {
					Thumbnails.of(targetFile).size(200, 200).toFile(new File(uploadPath, "s_" + uploadName));
				} catch (IOException ioE) {
					ioE.printStackTrace();
				} finally {
					log.info("===");
				}
			}
		} // end of for

		return uploadNameList;
	}

	// 파일 삭제
	protected void deleteFiles(List<String> fileNameList) {
		try {
			File uploadPath = new File("C:\\upload");

			for (String eachFileName : fileNameList) {
				// 파일 삭제
				File targetFile = new File(uploadPath, eachFileName);
				targetFile.delete();

				// 썸네일 삭제
				File targetThumb = new File(uploadPath, "s_" + eachFileName);
				targetThumb.delete();
			} // end of for
		} catch (Exception e) {
			// 예외 생략
		} finally {
			log.info("=== deleteFiles ===");
		}
	}
}
