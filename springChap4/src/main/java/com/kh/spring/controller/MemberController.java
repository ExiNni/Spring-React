package com.kh.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.model.Member;
import com.kh.spring.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
    private MemberService memberService;
	
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("member", new Member());
		return "register";
	}
	
	@PostMapping("/register")
    public String registerMember(Member member) {
        memberService.signUpMember(member);
        return "redirect:/member/register";
    }
}
