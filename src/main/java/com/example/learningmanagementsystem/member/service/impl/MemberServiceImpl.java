package com.example.learningmanagementsystem.member.service.impl;

import com.example.learningmanagementsystem.components.MailComponents;
import com.example.learningmanagementsystem.member.entity.Member;
import com.example.learningmanagementsystem.member.model.MemberInput;
import com.example.learningmanagementsystem.member.repository.MemberRepository;
import com.example.learningmanagementsystem.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;

    public boolean register(MemberInput parameter){
        memberRepository.findById(parameter.getUserId());

        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());
        if(optionalMember.isPresent()){
            return false;
        }

        String uuid = UUID.randomUUID().toString();

        Member member = new Member();
        member.setUserId(parameter.getUserId());
        member.setUserName(parameter.getUserName());
        member.setPhone(parameter.getPhone());
        member.setPassword(parameter.getPassword());
        member.setRegDt(LocalDateTime.now());

        member.setEmailAuthorized(false);
        member.setEmailAuthKey(uuid);
        memberRepository.save(member);

        String email = parameter.getUserId();
        String subject = "lms 회원가입을 축하드립니다.";
        String text = "<p>lms 사이트 가입을 축하드립니다.</p><p>아래 링크를 클릭하셔서 가입을 완료하세요.</p>"
                + "<div><a href='http://localhost:8080/member/email-auth?id="+ uuid +"'>가입완료</a></div>";
        mailComponents.sendMail(email, subject, text);
        return true;
    }

    @Override
    public boolean emailAuth(String uuid) {

        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
        if(!optionalMember.isPresent()) {
            return false;
        }

        Member member = optionalMember.get();
        member.setEmailAuthorized(true);
        member.setEmailAuthDt(LocalDateTime.now());
        memberRepository.save(member);

        return true;
    }
}
