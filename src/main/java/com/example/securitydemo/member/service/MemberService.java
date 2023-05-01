package com.example.securitydemo.member.service;

import com.example.securitydemo.member.Member;
import com.example.securitydemo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public Long join(Member member) {
        return memberRepository.save(member).getId();
    }

}
