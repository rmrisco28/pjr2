package com.example.prj2.member.controller;

import com.example.prj2.board.entity.Board;
import com.example.prj2.member.dto.MemberDto;
import com.example.prj2.member.entity.Member;
import com.example.prj2.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("join")
    public String join(Model model) {
//        model.addAttribute("memberDto", new MemberDto());
        return "member/join";
    }

    @PostMapping("join")
    public String joinPage(MemberDto memberDto, Model model) {
        String result = memberService.join(memberDto);

        switch (result) {
            case "empty" -> {
                model.addAttribute("error", "모든 항목을 입력해주세요.");
                return "member/join";
            }

            case "pwError" -> {
                model.addAttribute("pwError", "패스워드가 일치하지 않습니다.");
                return "member/join";
            }
            case "success" -> {
                return "redirect:/board/list";
            }
        }
        model.addAttribute("memberDto", memberDto);
        model.addAttribute("joinSuccess", "회원가입에 성공하였습니다.");
        return "board/list";
    }
}

