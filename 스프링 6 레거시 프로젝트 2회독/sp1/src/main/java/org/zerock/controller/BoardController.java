package org.zerock.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.AccountDTO;
import org.zerock.dto.BoardDTO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

//	@GetMapping("list")
//	public void list(Model model) {
//		
//		log.info("---------------------------------------");
//		log.info("board list");
//		
//		model.addAttribute("list", boardService.getList());
//		
//	}

//	@GetMapping("/list")
//	public void list( 
//			@RequestParam( name = "page", defaultValue = "1" ) int page,
//			@RequestParam( name = "size", defaultValue = "10" ) int size,
//			Model model) {
//		
//		log.info("page : " + page);
//		log.info("size : " + size);
//		
//		model.addAttribute("dto", boardService.getList(page, size));
//		
//	}

    @PreAuthorize("permitAll()")
    @GetMapping("/list")
    public void list(@RequestParam(name = "page", defaultValue = "1") int page,
	    @RequestParam(name = "size", defaultValue = "10") int size,
	    @RequestParam(name = "types", required = false) String types,
	    @RequestParam(name = "keyword", required = false) String keyword, Model model) {

	log.info("page : " + page);
	log.info("size : " + size);

	model.addAttribute("dto", boardService.getList(page, size, types, keyword));

    }

    @GetMapping("register")
    public void register() {
	log.info("---------------------------------------");
	log.info("board register");
    }

    @PostMapping("register")
    public String registerPost(BoardDTO dto, RedirectAttributes rttr) {
	log.info("---------------------------------------");
	log.info("board register post");

	Long bno = boardService.register(dto);

	rttr.addFlashAttribute("result", bno);

	return "redirect:/board/list";

    }

//	@PreAuthorize("isAuthenticated()")
//	@GetMapping("read/{bno}")
//	public String read( @PathVariable("bno")Long bno, Model model ) {
//		
//		log.info("---------------------------------------");
//		log.info("board read");
//		
//		BoardDTO dto = boardService.read(bno);
//		
//		model.addAttribute("board", dto);
//		
//		return "/board/read";
//		
//	}

//	@PreAuthorize("isAuthenticated()")
//	@GetMapping("read/{bno}")
//	public String read(Authentication authentication, @PathVariable("bno")Long bno, Model model ) {
//		
//		log.info("---------------------------------------");
//		log.info(authentication);
//		log.info("board read");
//		
//		
//		AccountDTO accountDTO = (AccountDTO)authentication.getPrincipal();
//		
//		log.info("--------AccountDTO------------");
//		log.info(accountDTO);
//		log.info("--------AccountDTO------------");
//		
//		
//		BoardDTO dto = boardService.read(bno);
//		
//		model.addAttribute("board", dto);
//		
//		return "/board/read";
//		
//	}

    @PreAuthorize("isAuthenticated()")
    @GetMapping("read/{bno}")
    public String read(@AuthenticationPrincipal AccountDTO accountDTO, @PathVariable("bno") Long bno, Model model) {

	log.info("---------------------------------------");
	log.info("board read");

	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	Object principal = auth.getPrincipal();

	log.info("== From SecurityContextHolder ==");
	log.info("Principal class: {}", principal.getClass().getName());
	log.info("Principal toString: {}", principal.toString());

	log.info("--------AccountDTO------------");
	log.info(accountDTO.toString());
	log.info("--------AccountDTO------------");

	BoardDTO dto = boardService.read(bno);

	model.addAttribute("board", dto);

	return "/board/read";

    }

    @GetMapping("modify/{bno}")
    public String modifyGET(@PathVariable("bno") Long bno, Model model) {

	log.info("---------------------------------------");
	log.info("board modify get");

	BoardDTO dto = boardService.read(bno);

	model.addAttribute("board", dto);

	return "/board/modify";

    }

    @PostMapping("modify")
    @PreAuthorize("authentication.name == #boardDTO.writer")
    public String modifyPOST(BoardDTO boardDTO) {

	log.info("---------------------------------------");
	log.info("board modify post");

	boardService.modify(boardDTO);

	return "redirect:/board/read/" + boardDTO.getBno();

    }

    @PostMapping("remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {

	log.info("---------------------------------------");
	log.info("board remove post");

	boardService.remove(bno);

	rttr.addFlashAttribute("result", bno);

	return "redirect:/board/list";

    }

}
