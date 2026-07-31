package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.BoardDTO;
import org.zerock.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@Log4j2
public class BoardController {
	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	// 게시물 목록 조회
	@GetMapping("/list")
	public String list(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model) {
		log.info("========== list ==========");
		log.info("========== page : {} ==========", page);
		log.info("========== size : {} ==========", size);
		model.addAttribute("dto", boardService.getList(page, size));
		return "/board/list.jsp";
	}

	// 게시물 등록화면 호출
	@GetMapping("/register")
	public String register() {
		log.info("========== register ==========");
		return "/board/register.jsp";
	}

	// 개시물 등록서비스 호출
	@PostMapping("/register")
	public String registerPOST(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
		log.info("========== registerPOST ==========");
		Long bno = boardService.register(boardDTO);
		redirectAttributes.addFlashAttribute("result", bno);
		return "redirect:/board/list"; // 목록으로 리디렉션
	}

	// 게시물 조회
	@GetMapping("/read/{bno}")
	public String read(@PathVariable("bno") Long bno, Model model) {
		log.info("========== read ==========");
		BoardDTO boardDTO = boardService.read(bno);
		model.addAttribute("board", boardDTO);
		return "/board/read.jsp";
	}

	// 게시물 수정화면 호출
	@GetMapping("/modify/{bno}")
	public String modifyGET(@PathVariable("bno") Long bno, Model model) {
		log.info("========== modifyGET ========== {}", bno);
		BoardDTO boardDTO = boardService.read(bno);
		model.addAttribute("board", boardDTO);
		return "/board/modify.jsp";
	}

	// 게시물 수정서비스 호출
	@PostMapping("/modify")
	public String modifyPOST(BoardDTO boardDTO) {
		log.info("========== modifyPOST ==========");
		boardService.modify(boardDTO);
		return "redirect:/board/read/" + boardDTO.getBno(); // 수정 후에 해당 게시물로
															// 리디렉션
	}

	// 게시물 삭제
	@PostMapping("/remove")
	public String removePOST(@RequestParam("bno") Long bno, RedirectAttributes redirectAttributes) {
		log.info("========== removePOST ==========");
		boardService.remove(bno);
		redirectAttributes.addFlashAttribute("result", bno);
		return "redirect:/board/list"; // 삭제 후에 목록으로 리디렉션
	}
}
