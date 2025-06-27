package com.example.prj2.board.entity;

import com.example.prj2.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "board")
public class Board {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member author;

    @CreationTimestamp
    private LocalDateTime created;

}
