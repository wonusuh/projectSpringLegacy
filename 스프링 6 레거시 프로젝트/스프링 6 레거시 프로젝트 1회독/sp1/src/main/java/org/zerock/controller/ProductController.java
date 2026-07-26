package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
@Log4j2
@RequiredArgsConstructor
public class ProductController {
	private final ProductService service;

	@GetMapping("register")
	public void registerGET() {
		log.info("product register");
	}

	@PostMapping("register")
	public String register(ProductDTO productDTO, @RequestParam("files") MultipartFile[] files,
			RedirectAttributes reAtr) {
		log.info("--------------------------------------------------------");
		log.info(productDTO);
		log.info(files);

		// upload files
		List<String> uploadNames = uploadFiles(files);
		uploadNames.forEach((eachName) -> {
			String uuid = eachName.substring(0, 36);
			String fileName = eachName.substring(37);
			log.info(uuid);
			log.info(fileName);
			productDTO.addImage(uuid, fileName);
		});

		Integer pno = service.register(productDTO);
		reAtr.addFlashAttribute("product", pno);
		return "redirect:/product/list";
	}

	private List<String> uploadFiles(MultipartFile[] files) throws RuntimeException {
		List<String> uploadNames = new ArrayList<>();

		//
		if (files == null || files.length == 0) {
			return uploadNames;
		}

		String uploadPath = "C:\\upload";
		log.info(
				"--------------------------------------------------------------uploadPath--------------------------------------------------------------");
		log.info(uploadPath);

		for (MultipartFile eachFile : files) {
			if (eachFile.isEmpty()) {
				continue;
			}

			String fileName = eachFile.getOriginalFilename();
			String uploadName = UUID.randomUUID().toString() + "_" + fileName;
			File targetFile = null;
			targetFile = new File(uploadPath, uploadName);

			try (InputStream fin = eachFile.getInputStream(); OutputStream fos = new FileOutputStream(targetFile);) {
				log.info(targetFile.getAbsolutePath());
				FileCopyUtils.copy(fin, fos);
				uploadNames.add(uploadName);
			} catch (Exception e) {
				log.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			} finally {
				//
			}

			//
			if (eachFile.getContentType().startsWith("image")) {
				try {
					Thumbnails.of(targetFile).size(200, 200).toFile(new File(uploadPath, "s_" + uploadName));
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					//
				}
			}
		}

		return uploadNames;
	}

	public void deleteFiles(List<String> fileNames) {
		try {
			File uploadPath = new File("C:\\upload");

			for (String eachFileName : fileNames) {
				File targetFile = new File(uploadPath, eachFileName);
				targetFile.delete();

				// 썸네일 파일도 지우기
				File targetThumb = new File(uploadPath, "s_" + eachFileName);
				targetThumb.delete();
			}
		} catch (Exception e) {
			//
		} finally {
			//
		}
	}

	@GetMapping("list")
	public void list(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model) {
		ProductListPagingDTO dto = service.getList(page, size);
		model.addAttribute("dto", dto);
	}

	@GetMapping("read/{pno}")
	public String read(@PathVariable("pno") Integer pno, Model model) {
		log.info("pno : ", pno);
		model.addAttribute("product", service.read(pno));
		return "/product/read";
	}

	@GetMapping("modify/{pno}")
	public String modifyGET(@PathVariable("pno") Integer pno, Model model) {
		log.info("pno : " + pno);
		model.addAttribute("product", service.read(pno));
		return "/product/modify";
	}

	@PostMapping("remove")
	public String remove(@RequestParam("pno") Integer pno, RedirectAttributes rttr) {
		service.remove(pno);
		rttr.addFlashAttribute("result", "deleted");
		return "redirect:/product/list";
	}

	@PostMapping("modify")
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

		// new
		if (newFileNames != null && newFileNames.size() > 0) {
			for (String newImage : newFileNames) {
				String uuid = newImage.substring(0, 36);
				String fileName = newImage.substring(37);
				productDTO.addImage(uuid, fileName);
			}
		}

		service.modify(productDTO);
		return "redirect:/product/read/" + productDTO.getPno();
	}
}
