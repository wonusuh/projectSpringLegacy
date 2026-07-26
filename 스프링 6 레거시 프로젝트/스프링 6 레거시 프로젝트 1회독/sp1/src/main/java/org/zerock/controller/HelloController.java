package org.zerock.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.SampleDTO;
import org.zerock.service.HelloService;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@ToString
@Log4j2
@RequestMapping("/sample")
public class HelloController {
	private final HelloService helloService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/ex1")
	public void ex1() {
		log.info("/sample/ex1");
		helloService.hello1();
	}

	@GetMapping("/ex2")
	public String ex2() {
		log.info("/sample/ex2");
		helloService.hello2("Wonu Suh");
		return "sample/success";
	}

	@GetMapping("/ex3")
	public String ex3() {
		log.info("/sample/ex3");
		return "redirect:/sample/ex3re";
	}

	@GetMapping("/ex3re")
	public String ex3Re() {
		log.info("/sample/ex3Re");
		return "sample/ex3Result";
	}

	@GetMapping("/ex4")
	public void ex4(@RequestParam(name = "n1", defaultValue = "1") int num, @RequestParam(name = "name") String name) {
		log.info("/sample/ex4");
		log.info("num : " + num);
		log.info("name : " + name);
	}

	@GetMapping("/ex5")
	public void ex5(SampleDTO dto) {
		log.info("/sample/ex5");
		log.info(dto);
	}

	@GetMapping("/ex6")
	public void ex6(Model model) {
		log.info("/sample/ex6");
		model.addAttribute("name", "Hong Gil Dong");
		model.addAttribute("age", 16);
		model.addAttribute("model", model);
	}

	@GetMapping("/ex7")
	public String ex7(RedirectAttributes rttr) {
		log.info("/sample/ex7");
		rttr.addAttribute("name", "Hong");
		rttr.addFlashAttribute("age", 34);
		return "redirect:/sample/ex8";
	}

	@GetMapping("/ex8")
	public void ex8() {
		log.info("/sample/ex8");
	}

	@GetMapping("access-denied")
	public String accessDenied() {
		return "/sample/accessDenied";
	}
}
