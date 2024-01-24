package com.example.learningmanagementsystem.member.repository;

import com.example.learningmanagementsystem.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
