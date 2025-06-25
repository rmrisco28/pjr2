package com.example.prj2.board;

import com.example.prj2.dto.BoardDto;
import com.example.prj2.entity.Board;
import com.example.prj2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String write(Board board, BoardDto boardDto) {
        boardService.write(board, boardDto);

        return "redirect:/board/list";
    }

    @GetMapping("list")
    public String list(@RequestParam(defaultValue = "1")
                       Integer page,
                       Model model) {
        // 조회
        List<Board> boardList = boardService.list(page);
        // 모델에 담기
        model.addAttribute("boardList", boardList);

        return "board/list";
    }

    @GetMapping("view")
    public String view(String id, Model model) {
        var view = boardService.view(id);

        model.addAttribute("view", view);
        return "board/view";
    }


}
