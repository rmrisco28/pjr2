package com.example.prj2.user.controller;

import com.example.prj2.user.dto.UserDto;
import com.example.prj2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @GetMapping("signUp")
    public String join() {
        return "user/signUp";
    }

    @PostMapping("signUp")
    public String join(UserDto dto, Model model) {
        userService.join(dto);
        model.addAttribute("user", dto);
        return "redirect:/board/list";
    }
}
