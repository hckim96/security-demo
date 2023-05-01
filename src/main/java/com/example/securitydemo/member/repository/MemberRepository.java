package com.example.securitydemo.member.repository;

import com.example.securitydemo.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByLoginId(String loginId);
}
