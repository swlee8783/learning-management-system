package com.example.learningmanagementsystem.member.controller;

import com.example.learningmanagementsystem.member.model.MemberInput;
import com.example.learningmanagementsystem.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/register")
    public String register(){
        return "member/register";
    }

    @PostMapping("/member/register")
    public String register(Model model, HttpServletRequest request,
                           MemberInput parameter){

        boolean result = memberService.register(parameter);
        model.addAttribute("result", result);

        return "member/register_complete";

    }

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request){
        String id = request.getParameter("id");
        System.out.println(id);

        boolean result = memberService.emailAuth(id);
        model.addAttribute("result", result);

        return "member/email-auth";
    }
}
