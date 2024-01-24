package com.example.learningmanagementsystem.member.service;

import com.example.learningmanagementsystem.member.entity.Member;
import com.example.learningmanagementsystem.member.model.MemberInput;
import com.example.learningmanagementsystem.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public boolean register(MemberInput parameter){
        memberRepository.findById(parameter.getUserId());

        Member member = new Member();
        member.setUserId(parameter.getUserId());
        member.setUserName(parameter.getUserName());
        member.setPhone(parameter.getPhone());
        member.setPassword(parameter.getPassword());
        member.setRegDt(LocalDateTime.now());
        memberRepository.save(member);

        return false;
    }
}
