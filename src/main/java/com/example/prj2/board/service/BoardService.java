package com.example.prj2.board.service;

import com.example.prj2.board.dto.BoardDto;
import com.example.prj2.board.entity.Board;
import com.example.prj2.board.repository.BoardRepository;
import com.example.prj2.member.Member;
import com.example.prj2.member.MemberDto;
import com.example.prj2.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    public boolean write(BoardDto boardDto) {
        if (!boardDto.getTitle().isBlank() &&
                !boardDto.getContent().isBlank() &&
                !boardDto.getAuthor().isBlank()) {
            Optional<Member> byId = memberRepository.findById(boardDto.getAuthor());
            if (byId.isPresent()) {
                return false;
            }
            Member member = byId.get();

            Board board = new Board();
            board.setTitle(boardDto.getTitle());
            board.setContent(boardDto.getContent());
            board.setAuthor(member);

            boardRepository.save(board);
            return true;
        } else {
            return false;
        }
    }

    public Map<String, Object> list(Integer page) {
        // 게시물 10개씩 가모두 가져오기
        Page<Board> boardPage = boardRepository.findAll(
                PageRequest.of(page - 1, 10, Sort.by("id").descending()));
        // 현재 보여줄 게시글
        List<Board> content = boardPage.getContent();
        int totalPages = boardPage.getTotalPages();
        Integer rightPageNumber = ((page - 1) / 10 + 1) * 10;
        Integer leftPageNumber = Math.max(1, rightPageNumber - 9);
        rightPageNumber = Math.min(rightPageNumber, totalPages);

        var result = Map.of(
                "boardPage", boardPage,
                "boardList", content,
                "rightPageNumber", rightPageNumber,
                "leftPageNumber", leftPageNumber,
                "totalPage", totalPages,
                "currentPage", page
        );
        return result;
    }

    public BoardDto get(String id, Member login) {
        Board board = boardRepository.findById(id).orElseThrow();

        BoardDto dto = new BoardDto();

        if (!board.getAuthor().getId().equals(login.getId())) {
            return null;
        }
        // 조회
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setAuthor(board.getAuthor().getId());
        dto.setCreated(board.getCreated());

        return dto;
    }

    public void update(BoardDto boardDto) {
        Board board = boardRepository.findById(boardDto.getId()).get();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        boardRepository.save(board);
    }

    public void delete(String id) {
        boardRepository.deleteById(id);
    }

}
