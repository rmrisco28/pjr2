package com.example.prj2.member.service;

import com.example.prj2.member.dto.MemberDto;
import com.example.prj2.member.entity.Member;
import com.example.prj2.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public String join(MemberDto memberDto) {
        if (memberDto.getId().isBlank() ||
                memberDto.getPassword().isBlank() ||
                memberDto.getPasswordDuplication().isBlank() ||
                memberDto.getName().isBlank() ||
                memberDto.getNickname().isBlank()) {
            return "empty";
        }
        if (!memberDto.getPassword().equals(memberDto.getPasswordDuplication())) {
            return "pwError";
        }
        Member member = new Member();
        member.setId(memberDto.getId());
        member.setPassword(memberDto.getPassword());
        member.setPasswordDuplication(memberDto.getPasswordDuplication());
        member.setName(memberDto.getName());
        member.setNickname(memberDto.getNickname());

        memberRepository.save(member);
        return "success";
    }

}
