package com.example.learningmanagementsystem.member.service;

import com.example.learningmanagementsystem.member.model.MemberInput;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    boolean register(MemberInput parameter);

    /**uuid 해당 계정 활성화**/
    boolean emailAuth(String uuid);
}
