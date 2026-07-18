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

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시물 목록 조회
    @GetMapping("/list")
    public void list(@RequestParam(name = "page", defaultValue = "1") int page,
	    @RequestParam(name = "size", defaultValue = "10") int size,
	    @RequestParam(name = "types", required = false) String types,
	    @RequestParam(name = "keyword", required = false) String keyword, Model model) {
//	log.info("----------");
//	log.info("board list");
//	model.addAttribute("list", boardService.getList());

	log.info("page : " + page);
	log.info("size : " + size);
	model.addAttribute("dto", boardService.getList(page, size, types, keyword));
    }

    // 게시물 등록화면 호출
    @GetMapping("/register")
    public void register() {
	log.info("----------");
	log.info("board register");
    }

    // 게시물 등록 요청
    @PostMapping("/register")
    public String registerPost(BoardDTO boardDTO, RedirectAttributes rttr) {
	log.info("----------");
	log.info("board register post");
	Long bno = boardService.register(boardDTO);
	rttr.addFlashAttribute("result", bno);
	return "redirect:/board/list";
    }

    // 게시물 조회
    @GetMapping("/read/{bno}")
    public String read(@PathVariable("bno") Long bno, Model model) {
	log.info("----------");
	log.info("board read");
	BoardDTO boardDTO = boardService.read(bno);
	model.addAttribute("board", boardDTO);
	return "/board/read";
    }

    // 게시물 수정화면 호출
    @GetMapping("/modify/{bno}")
    public String modifyGET(@PathVariable("bno") Long bno, Model model) {
	log.info("----------");
	log.info("board modify get");
	BoardDTO boardDTO = boardService.read(bno);
	model.addAttribute("board", boardDTO);
	return "/board/modify";
    }

    // 게시물 수정 서비스
    @PostMapping("/modify")
    public String modifyPOST(BoardDTO boardDTO) {
	log.info("----------");
	log.info("board modify POST");
	boardService.modify(boardDTO);
	return "redirect:/board/read/" + boardDTO.getBno();
    }

    // 게시물 삭제
    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
	log.info("----------");
	log.info("board remove POST");
	boardService.remove(bno);
	rttr.addFlashAttribute("result", bno);
	return "redirect:/board/list";
    }
}
