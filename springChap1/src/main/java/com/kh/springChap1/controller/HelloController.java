package com.kh.springChap1.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
  allowCredentials: 브라우저에서 요청에 대한 응답을 할 때, 요청에 인증 정보 (쿠키, HTTP인증)을 포함할 것인지를 나타낸 것
  					인증 정보를 포함한 요청을 서버로 전송할 수 있게 해줌
  					인증 정보를 서로 주고 받을 수 있게 해주는 역할을 함
*/


@RestController
@RequestMapping("/api")
// cors: 쿠키나 세션 무언가 접속하는 것을 허용해 줄 때 사용
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class HelloController {

	@GetMapping("/hello")
	public String getHello() {
		return "Hello Spring!";
	}
	
	@GetMapping("/java")
	public String getJava() {
		return "Spring boot test code";
	}
	
	@GetMapping("/react")
	public String getReact() {
		return "react";
	}
}
