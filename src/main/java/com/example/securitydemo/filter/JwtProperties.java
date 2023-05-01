package com.example.securitydemo.filter;

public interface JwtProperties {
    String SECRET = "secret test";
    int EXPIRATION_TIME = 60 * 1000;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
