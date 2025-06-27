package com.example.prj2.board.controller;


import com.example.prj2.board.dto.BoardDto;
import com.example.prj2.board.entity.Board;
import com.example.prj2.board.service.BoardService;
import com.example.prj2.member.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("write")
    public String write(HttpSession session, RedirectAttributes rttr) {
        Object login = session.getAttribute("login");
        if (login == null) {
            rttr.addFlashAttribute("loginRequired", "로그인이 필요합니다.");
            return "redirect:/member/login";
        } else {
            return "board/write";

        }
    }

    @PostMapping("write")
    public String write(BoardDto boardDto, Model model, HttpSession session) {
        boolean result = boardService.write(boardDto);
        if (result) {
            Member login = (Member) session.getAttribute("login");
            boardDto.setAuthor(login.getId());
            return "redirect:/board/list";
        } else {
            model.addAttribute("error", "모든 항목을 입력해주세요");
            model.addAttribute("boardDto", boardDto);
            return "board/write";
        }
    }

    @GetMapping("list")
    public String list(@RequestParam(defaultValue = "1") Integer page, Model model) {
        if (page == null || page < 1) page = 1;

        // 조회
        Map<String, Object> list = boardService.list(page);
        // 모델에 담기
        model.addAllAttributes(list);
        return "board/list";
    }

    @GetMapping("view")
    public String view(String id, Model model, @RequestParam(required = false, defaultValue = "1") Integer page, HttpSession session) {
        Member login = (Member) session.getAttribute("login");

        var view = boardService.get(id, login);

        model.addAttribute("view", view);
        model.addAttribute("page", page);
        return "board/view";
    }

    @GetMapping("edit")
    public String edit(String id, Model model, RedirectAttributes rttr, HttpSession session) {
        Member login = (Member) session.getAttribute("login");
        if (login == null) {
            rttr.addFlashAttribute("loginRequired", "로그인이 필요합니다.");
            return "redirect:/member/login";
        } else {
            BoardDto dto = boardService.get(id, login);
            if (dto == null) {
                rttr.addFlashAttribute("error", "작성자만 수정 할 수 있습니다.");
                return "redirect:/board/list";
            }
            model.addAttribute("board", dto);
            return "board/edit";
        }
    }

    @PostMapping("edit")
    public String edit(String id, BoardDto boardDto, RedirectAttributes rttr) {
        boardService.update(boardDto);
        rttr.addFlashAttribute("update", id + "번 게시글이 수정되었습니다.");

        return "redirect:/board/view?id=" + id;
    }

    @PostMapping("delete")
    public String delete(String id, RedirectAttributes rttr, HttpSession session) {
        Object login = session.getAttribute("login");
        if (login == null) {
            rttr.addFlashAttribute("loginRequired", "로그인이 필요합니다.");
            return "redirect:/member/login";
        } else {
            boardService.delete(id);
            rttr.addFlashAttribute("deleteMessage", id + "번 게시물이 삭제되었습니다.");
            return "redirect:/board/list";
        }
    }

}
