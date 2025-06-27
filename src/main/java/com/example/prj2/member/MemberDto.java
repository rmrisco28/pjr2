package com.example.prj2.member;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Member}
 */
@Data
public class MemberDto implements Serializable {
    String id;
    String password;
    String name;
    String nickname;


    LocalDateTime registrationDate;
}