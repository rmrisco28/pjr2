package com.example.prj2.board.controller;


import com.example.prj2.board.dto.BoardDto;
import com.example.prj2.board.entity.Board;
import com.example.prj2.board.service.BoardService;
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
    public String write() {
        return "board/write";
    }

    @PostMapping("write")
    public String write(BoardDto boardDto, Model model) {
        boolean result = boardService.write(boardDto);
        if (result) {
            return "redirect:/board/list";
        } else {
            model.addAttribute("error", "모든 항목을 입력해주세요");
            model.addAttribute("boardDto", boardDto);
            return "board/write";
        }
    }

    @GetMapping("list")
    public String list(@RequestParam(defaultValue = "1")
                       Integer page,
                       Model model) {
        if (page == null || page < 1) page = 1;

        // 조회
        Map<String, Object> list = boardService.list(page);
        // 모델에 담기
        model.addAllAttributes(list);
        return "board/list";
    }

    @GetMapping("view")
    public String view(String id, Model model,
                       @RequestParam(required = false, defaultValue = "1")
                       Integer page) {
        var view = boardService.get(id);

        model.addAttribute("view", view);
        model.addAttribute("page", page);
        return "board/view";
    }

    @GetMapping("edit")
    public String edit(String id, Model model) {
        com.example.prj2.board.dto.BoardDto boardDto = boardService.get(id);
        model.addAttribute("board", boardDto);
        return "board/edit";
    }

    @PostMapping("edit")
    public String edit(String id,
                       com.example.prj2.board.dto.BoardDto boardDto,
                       RedirectAttributes rttr) {
        boardService.update(boardDto);
        rttr.addFlashAttribute("update", id + "번 게시글이 수정되었습니다.");

        return "redirect:/board/view?id=" + id;
    }

    @PostMapping("delete")
    public String delete(String id, RedirectAttributes rttr) {
        boardService.delete(id);
        rttr.addFlashAttribute("deleteMessage", id + "번 게시물이 삭제되었습니다.");
        return "redirect:/board/list";
    }
}
