package com.example.prj2.board.user.service;

import com.example.prj2.board.user.dto.UserDto;
import com.example.prj2.board.user.entity.User;
import com.example.prj2.board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public void join(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setNickname(userDto.getNickname());

        userRepository.save(user);
    }
}
