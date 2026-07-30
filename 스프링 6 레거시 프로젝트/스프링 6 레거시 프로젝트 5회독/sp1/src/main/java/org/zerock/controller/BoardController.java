package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@Log4j2
public class BoardController {
	// 게시물 목록 조회
	@GetMapping("/list")
	public String list() {
		log.info("========== list ==========");
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
	public String registerPOST() {
		log.info("========== registerPOST ==========");
		return "redirect:/board/list"; // 목록으로 리디렉션
	}

	// 게시물 조회
	@GetMapping("/read/{bno}")
	public String read(@PathVariable("bno") Long bno) {
		log.info("========== read ==========");
		return "/board/read.jsp";
	}

	// 게시물 수정화면 호출
	@GetMapping("/modify/{bno}")
	public String modifyGET(@PathVariable("bno") Long bno) {
		log.info("========== modifyGET ========== {}", bno);
		return "/board/modify.jsp";
	}

	// 게시물 수정서비스 호출
	@PostMapping("/modify")
	public String modifyPOST() {
		log.info("========== modifyPOST ==========");
		return "redirect:/board/read/123"; // 수정 후에 해당 게시물로 리디렉션
	}
}
