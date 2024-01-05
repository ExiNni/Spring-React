package com.kh.spring.service;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.spring.model.UserSNS;
import com.kh.spring.repository.UserRepository;

public class UserService {
	
	@Autowired
	private final UserRepository userRepository;
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void googleLoginService(@AuthenticationPrincipal OAuth2User principal, Model model) {
		model.addAttribute("name", principal.getAttribute("name"));
		model.addAttribute("email", principal.getAttribute("email"));
	}

	public void naverLoginService(OAuth2User principal, String naverResponse, Model model) {

		System.out.println("OAuth2User Attributes : " + principal.getAttributes());
		String name = null;
		String email = null;

		if (naverResponse != null) {
			JsonNode responseNode;
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				responseNode = objectMapper.readTree(naverResponse).get("response");

				if (responseNode != null) {
					name = responseNode.get("name").asText();
					email = responseNode.get("email").asText();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// OAuth2User에서 이름과 이메일을 추출
		if (name == null || email == null) {
			String principalName = principal.getName();

			// principal.getName()으로 가지고온 정보에서 이메일과 이름만 출력
//		replaceAll - 문자열에서 공백이나 숫자 등 패턴을 찾을 때 와주는 식
			String[] keyValue = principalName.replaceAll("[{}]", "").split(",");
			for (String pair : keyValue) {
				String[] entry = pair.split("=");
				if (entry.length == 2) {
					String key = entry[0].trim();
					String value = entry[1].trim();
					if ("name".equals(key)) {
						name = value;
					} else if ("email".equals(key)) {
						email = value;
					}
				}
			}
		}

		// 사용자 정보를 DB에 저장
		String provider = principal.getName();

		System.out.println("UserController 82 ↓");
		System.out.println("String principalName = principal.getName() : " + provider);
		// 1. model
		UserSNS user = new UserSNS();
		user.setName(name);
		user.setEmail(email);
		user.setProvider(provider);

		// 저장
		userRepository.save(user);

		model.addAttribute("name", name);
		model.addAttribute("email", email);

		// 모델이 naverResponse에 들어가야하는 경우 Naver 응답 추가
		model.addAttribute("naverResponse", naverResponse);
	}
	
	public void kakaoLoginService(@AuthenticationPrincipal OAuth2User principal, Model model) {
		model.addAttribute("name", principal.getAttribute("name"));
		model.addAttribute("email", principal.getAttribute("email"));
	}
}
