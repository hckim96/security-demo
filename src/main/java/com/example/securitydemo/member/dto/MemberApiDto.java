package com.example.securitydemo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MemberApiDto {

    @Data
    public static class LoginRequest {
        private String loginId;
        private String password;
    }

    @Data
    public static class LoginResponse {
        private Long loginId;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignupRequest {
        private String loginId;
        private String password;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignupResponse {
        private Long memberId;
    }


}
