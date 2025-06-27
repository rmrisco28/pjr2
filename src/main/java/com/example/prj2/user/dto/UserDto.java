package com.example.prj2.user.dto;

import com.example.prj2.user.entity.User;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link User}
 */
@Value
public class UserDto implements Serializable {
    String id;
    String password;
    String name;
    String nickname;
    LocalDateTime registrationDate;
}