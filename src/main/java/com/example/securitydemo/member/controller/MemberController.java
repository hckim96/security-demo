package com.example.securitydemo.member.controller;

import com.example.securitydemo.member.Member;
import com.example.securitydemo.member.dto.MemberApiDto.SignupRequest;
import com.example.securitydemo.member.dto.MemberApiDto.SignupResponse;
import com.example.securitydemo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> postSignup(@RequestBody SignupRequest signupRequest) {
        log.info("MemberController.postSignup");
        log.info("signupRequest = " + signupRequest);

        Long join = memberService.join(Member
                .builder()
                .loginId(signupRequest.getLoginId())
                .password(bCryptPasswordEncoder.encode(signupRequest.getPassword()))
                .build());

        return ResponseEntity.ok(new SignupResponse(join));
    }

}
