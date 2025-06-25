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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        List<Board> boardList = boardService.list();
        // list 목록 만들기
//        Map<String, Object> number = boardService.number(page);


        // 모델에 담기
        model.addAttribute("boardList", boardList);
//        model.addAllAttributes(number);
        return "board/list";
    }

    @GetMapping("view")
    public String view(String id, Model model) {
        var view = boardService.get(id);

        model.addAttribute("view", view);
        return "board/view";
    }

    @GetMapping("edit")
    public String edit(String id, Model model) {
        BoardDto boardDto = boardService.get(id);
        model.addAttribute("board", boardDto);
        return "board/edit";
    }

    @PostMapping("edit")
    public String edit(String id,
                       BoardDto boardDto,
                       RedirectAttributes rttr) {
        boardService.update(boardDto);
        rttr.addFlashAttribute("update", id + "번 게시글이 수정되었습니다.");

        return "redirect:/board/list";
    }

    @PostMapping("delete")
    public String delete(String id, RedirectAttributes rttr) {
        boardService.delete(id);
        rttr.addFlashAttribute("deleteMessage", id + "번 게시물이 삭제되었습니다.");
        return "redirect:/board/list";
    }
}
