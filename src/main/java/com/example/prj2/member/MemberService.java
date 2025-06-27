package com.example.prj2.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    public boolean signUp(MemberDto memberDto) {
        if (!memberDto.getId().isBlank() &&
                !memberDto.getPassword().isBlank() &&
                !memberDto.getNickname().isBlank() &&
                !memberDto.getNickname().isBlank()) {
            Member member = new Member();
            member.setId(memberDto.getId());
            member.setPassword(memberDto.getPassword());
            member.setName(memberDto.getName());
            member.setNickname(memberDto.getNickname());
            memberRepository.save(member);
            return true;
        } else {

            return false;
        }
    }

    public List<Member> memberList() {
        List<Member> result = memberRepository.findAllBy();
        return result;

    }

    public Member info(String id) {
        Member member = memberRepository.findById(id).get();
        return member;
    }

    public void delete(String id) {
        memberRepository.deleteById(id);
    }

    public Member edit(String id) {
        return memberRepository.findById(id).get();
    }

    public void update(MemberDto memberDto) {
        // 조회
        Member member = memberRepository.findById(memberDto.getId()).get();
        // 저장
        member.setPassword(memberDto.getPassword());
        member.setName(memberDto.getName());
        member.setNickname(memberDto.getNickname());
        memberRepository.save(member);
    }

    public Member login(String id, String password) {
        Optional<Member> optional = memberRepository.findById(id);
        if (optional.isPresent()) {
            Member member = optional.get();
            if (member.getPassword().equals(password)) {
                return member; // 로그인 성공
            }
        }
        return null;
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
