package com.example.prj2.board.dto;

import com.example.prj2.board.entity.Board;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Board}
 */
@Data
public class BoardDto implements Serializable {
    String id;
    @NotBlank(message = "제목은 필수입니다.")
    String title;
    @NotBlank(message = "내용은 필수입니다.")
    String content;
    @NotBlank(message = "작성자는 필수입니다.")
    String author;
    LocalDateTime created;
}