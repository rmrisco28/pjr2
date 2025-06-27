package com.example.prj2.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {
    public final MemberService memberService;

    @GetMapping("signup")
    public String signUp() {
        return "member/signup";
    }

    @PostMapping("signup")
    public String signUp(MemberDto memberDto, Model model, RedirectAttributes rttr) {
        boolean result = memberService.signUp(memberDto);
        if (result) {
            model.addAttribute("memberDto", memberDto);
            rttr.addFlashAttribute("signup", "회원가입이 완료되었습니다.");
            return "redirect:/member/login";
        } else {
            model.addAttribute("fail", "모든 항목을 입력해주세요.");
            model.addAttribute("dto", memberDto);
            return "member/signup";
        }
    }

    @GetMapping("memberList")
    public String memberList(Model model) {
        List<Member> members = memberService.memberList();
        model.addAttribute("list", members);
        return "member/memberList";
    }

    @GetMapping("info")
    public String info(String id, Model model) {
        Member info = memberService.info(id);
        model.addAttribute("memberDto", info);
        return "member/info";
    }

    @PostMapping("delete")
    public String delete(String id, RedirectAttributes rttr) {
        memberService.delete(id);
        rttr.addFlashAttribute("delete", "회원이 탈퇴되었습니다.");
        return "redirect:/member/memberList";
    }

    @GetMapping("edit")
    public String edit(String id, Model model) {
        Member edit = memberService.edit(id);
        model.addAttribute("member", edit);
        return "member/edit";
    }

    @PostMapping("edit")
    public String edit(MemberDto memberDto, RedirectAttributes rttr) {
        memberService.update(memberDto);
        rttr.addFlashAttribute("update", "회원 정보가 수정되었습니다.");
        return "redirect:/member/info?id=" + memberDto.getId();
    }

    @GetMapping("login")
    public String login() {
        return "member/login";
    }

    @PostMapping("login")
    public String login(String id, String password, HttpSession session, RedirectAttributes rttr, Model model) {
        Member login = memberService.login(id, password);
        if (login != null) {
            session.setAttribute("login", login);
            rttr.addFlashAttribute("login", "로그인에 성공하였습니다.");
            return "redirect:/board/list";
        } else {
            rttr.addFlashAttribute("fail",
                    "로그인에 실패하였습니다.<br>아이디 또는 비밀번호를 확인해주세요.");
            rttr.addFlashAttribute("failId", id);
            return "redirect:/member/login";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        memberService.logout(session);
        return "redirect:/member/login";
    }
}
