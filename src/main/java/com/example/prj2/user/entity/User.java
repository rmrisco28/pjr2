package com.example.prj2.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user")
public class User {
    @Id
    private String id;

    private String password;
    private String name;
    private String nickname;

    @Column(name = "registration_date", insertable = false, updatable = false)
    private LocalDateTime registrationDate;
}
