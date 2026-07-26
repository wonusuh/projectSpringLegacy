package org.zerock.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public void hello1() {
    }

    public String hello2(String name) {
	return "Hello " + name;
    }
}
