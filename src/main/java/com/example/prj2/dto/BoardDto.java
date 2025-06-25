package com.example.prj2.dto;

import com.example.prj2.entity.Board;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Board}
 */
@Data
public class BoardDto implements Serializable {
    String id;
    String title;
    String content;
    String author;
    LocalDateTime created;
}