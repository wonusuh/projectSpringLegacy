package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    @GetMapping("/list")
    public void list() {
	log.info("----------");
	log.info("board list");
    }

    @GetMapping("/register")
    public void register() {
	log.info("----------");
	log.info("board register");
    }

    @PostMapping("/register")
    public String registerPost() {
	log.info("----------");
	log.info("board register post");
	return "redirect:/board/list";
    }

    @GetMapping("/read/{bno}")
    public String read(@PathVariable("bno") Long bno) {
	log.info("----------");
	log.info("board read");
	return "/board/read";
    }

    @GetMapping("/modify/{bno}")
    public String modifyGET(@PathVariable("bno") Long bno) {
	log.info("----------");
	log.info("board modify get");
	return "/board/modify";
    }

    @PostMapping("/modify")
    public String modifyPOST() {
	log.info("----------");
	log.info("board modify POST");
	return "redirect:/board/read/7150";
    }
}
