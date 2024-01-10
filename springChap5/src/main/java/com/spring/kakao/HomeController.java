package com.spring.kakao;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.kakao.service.KakaoService;
import com.spring.naver.service.NaverService;

import lombok.RequiredArgsConstructor;

// 응답받은 결과를 담기위한 Entity
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final KakaoService kakaoService;
    private final NaverService naverService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());
        model.addAttribute("naverUrl", naverService.getNaverLogin());

        return "index";
    }
	
}
