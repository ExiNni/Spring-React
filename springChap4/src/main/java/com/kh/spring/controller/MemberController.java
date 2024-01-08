package com.kh.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "redirect:/member/all";
    }
	
	@GetMapping("/all")
    public String showAllMembers(Model model) {
        List<Member> member = memberService.getAllMembers();
        model.addAttribute("member", member);
        return "allMembers";
    }
	
	@PostMapping("/update")
    public String updateMember(Member member) {
        memberService.updateMember(member);
        return "redirect:/member/all";
    }
	
	@GetMapping("/update")
	public String showUpdateForm(@RequestParam("id") long id, Model model) {
	    Member member = memberService.getMemberById(id);
	    model.addAttribute("member", member);
	    return "updateMember"; 
	}

	@GetMapping("/delete")
	public String deleteMember(@RequestParam("id") long id) {
	    memberService.deleteMember(id);
	    return "redirect:/member/all";
	}
}
