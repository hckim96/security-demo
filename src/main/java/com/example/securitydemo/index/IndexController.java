package com.example.securitydemo.index;

import com.example.securitydemo.config.auth.MemberDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
public class IndexController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        log.info("IndexController.hello");
        log.info("authentication = " + authentication);
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        log.info("memberDetails.getUsername() = " + memberDetails.getUsername());
        log.info("memberDetails.getPassword() = " + memberDetails.getPassword());
        return "hello";
    }
}
