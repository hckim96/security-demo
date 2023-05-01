package com.example.securitydemo.config.auth;

import com.example.securitydemo.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
public class MemberDetails implements UserDetails {
    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.info("MemberDetails.getAuthorities");
        return null;
    }

    @Override
    public String getPassword() {
        log.info("MemberDetails.getPassword");
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        log.info("MemberDetails.getUsername");
        return member.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        log.info("MemberDetails.isAccountNonExpired");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        log.info("MemberDetails.isAccountNonLocked");
        // called first after
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        log.info("MemberDetails.isCredentialsNonExpired");
        return true;
    }

    @Override
    public boolean isEnabled() {
        log.info("MemberDetails.isEnabled");
        return true;
    }
}
