package com.example.prj2.board.user.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.prj2.board.user.entity.User}
 */
@Value
public class UserDto implements Serializable {
    String id;
    String password;
    String name;
    String nickname;
    LocalDateTime registrationDate;
}