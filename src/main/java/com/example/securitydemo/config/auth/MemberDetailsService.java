package com.example.securitydemo.config.auth;

import com.example.securitydemo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service // 이거 없으면 proxy 관련 문제
@Slf4j
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("MemberDetailsService.loadUserByUsername");
        return new MemberDetails(memberRepository.findMemberByLoginId(username).orElseThrow(() -> new RuntimeException("no user with loginId: " + username)));
    }

}
