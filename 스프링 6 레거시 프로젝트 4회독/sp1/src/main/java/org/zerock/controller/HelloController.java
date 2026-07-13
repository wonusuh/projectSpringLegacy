package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.service.HelloService;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/sample")
@RequiredArgsConstructor
@ToString
@Log4j2
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/ex1")
    public void ex1() {
	log.info("/sample/ex1");
    }
}
