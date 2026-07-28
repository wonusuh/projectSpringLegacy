package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.SampleDTO;
import org.zerock.service.HelloService;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/sample")
@ToString
@Log4j2
public class HelloController {
	private final HelloService helloService;

	@Autowired
	public HelloController(HelloService helloService) {
		this.helloService = helloService;
	}

	@GetMapping("/ex1")
	public String ex1() {
		log.info("===== /sample/ex1 =====");
		return "/sample/ex1.jsp";
	}

	@GetMapping("/ex2")
	public String ex2() {
		log.info("===== /sample/ex2 =====");
		return "/sample/success.jsp";
	}

	@GetMapping("/ex3")
	public String ex3() {
		log.info("===== /sample/ex3 =====");
		return "redirect:/sample/ex3re";
	}

	@GetMapping("/ex3re")
	public String ex3Re() {
		log.info("===== /sample/ex3Re =====");
		return "/sample/ex3Result.jsp";
	}

	@GetMapping("/ex4")
	public String ex4(@RequestParam(name = "n1", defaultValue = "1") int num,
			@RequestParam(name = "name") String name) {
		log.info("===== /sample/ex4 =====");
		log.info("num : " + num);
		log.info("name : " + name);
		return "/sample/ex4.jsp";
	}

	@GetMapping("/ex5")
	public String ex5(SampleDTO sampleDTO) {
		log.info("===== /sample/ex5 =====");
		log.info("sampleDTO : " + sampleDTO);
		return "/sample/ex5.jsp";
	}

	@GetMapping("/ex6")
	public String ex6(Model model) {
		log.info("===== /sample/ex6 =====");
		model.addAttribute("name", "Wonu Suh");
		model.addAttribute("age", 35);
		return "/sample/ex6.jsp";
	}

	@GetMapping("/ex7")
	public String ex7(RedirectAttributes redirectAttributes) {
		log.info("===== /sample/ex7 =====");
		redirectAttributes.addAttribute("name", "wonuSuh");
		redirectAttributes.addFlashAttribute("age", 35);
		return "redirect:/sample/ex8";
	}

	@GetMapping("/ex8")
	public String ex8() {
		log.info("===== /sample/ex8 =====");
		return "/sample/ex8.jsp";
	}
}
