package com.spring.kakao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.kakao.common.MsgEntity;
import com.spring.kakao.dto.KakaoDTO;
import com.spring.kakao.service.KakaoService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class KakaoController {
	private final KakaoService kakaoService;
	
	// 결과에 대한 내용을 담을 Entity
	@GetMapping("/callback")
	public ResponseEntity<MsgEntity> callback(HttpServletRequest request) throws Exception {
		
		KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"));
		
		return ResponseEntity.ok()
				.body(new MsgEntity("Success", kakaoInfo));
				
	}
}