package com.example.prj2.service;

import com.example.prj2.dto.BoardDto;
import com.example.prj2.entity.Board;
import com.example.prj2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;


    public void write(Board board, BoardDto boardDto) {
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setAuthor(boardDto.getAuthor());

        Board save = boardRepository.save(board);
        System.out.println("저장 성공");
        System.out.println(save);
    }

    public List<Board> list(Integer page) {
/*        // 1. 한페이지에 10개씩 가져오도록 설정
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by("id"));
        // 2. 해당 페이지의 게시글 목록 가져오기
        Page<Board> boardPage = boardRepository.findAll(pageRequest);
        // 3. 현재 보여줄 게시글 목록
        List<Board> boardList = boardPage.getContent();
        // 4. 페이지 네이션 계산
        int totalPages = boardPage.getTotalPages();
        int currentPage = pageRequest.getPageNumber();
        int rightPageNumber = ((currentPage - 1) / 10 + 1) * 10;
        int leftPageNumber = rightPageNumber - 9;

        rightPageNumber = Math.min(rightPageNumber, totalPages);


        // 5. 결과값을 넘기기 위해 Map으로 리턴
        Map<String, Object> result = Map.of(
                "boardList", boardList,
                "totalPages", totalPages,
                "currentPage", currentPage,
                "leftPageNumber", leftPageNumber,
                "rightPageNumber", rightPageNumber
        );*/ // chat gpt가 알려준 코드 근데 잘 모르겠어서 패스
        List<Board> list = boardRepository.findAll();

        return list;
    }

    public BoardDto view(String id) {
        Board board = boardRepository.findById(id).get();
        BoardDto dto = new BoardDto();
        // 조회
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setAuthor(board.getAuthor());
        dto.setCreated(board.getCreated());

        return dto;
    }

}
