package com.example.securitydemo.config.security;

import com.example.securitydemo.filter.JwtAuthenticationFilter;
import com.example.securitydemo.filter.JwtAuthorizationFilter;
import com.example.securitydemo.filter.LogFilter;
import com.example.securitydemo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final MemberRepository memberRepository;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .addFilterBefore(new LogFilter(), CorsFilter.class)
                .csrf().disable() // rest api 에서 csrf 방어 필요 없음
                .cors().configurationSource(corsConfigurationSource()).and() // cors
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()// 세션 생성 안함 ,, response 에 setcookie jsessionId= ... 안함
                .formLogin().disable() // 초기 로그인화면 자동생성 안함
//                .httpBasic().disable()  // 요청마다 Authorization 헤더에 아이디,패스워드 base64 인코딩한 문자열 보내야함 default : disable
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), memberRepository))

                .authorizeRequests()
                .antMatchers("/api/v1/member/login", "/api/v1/member/signup").permitAll()
                .anyRequest().authenticated()

                .and()


                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*localhost*"); // A list of origins for which cross-origin requests are allowed. ex) http://localhost:8080
        configuration.addAllowedHeader("*"); // Set the HTTP methods to allow ,ex) "GET", "POST", "PUT";
        configuration.addAllowedMethod("*"); // Set the list of headers that a pre-flight request can list as allowed for use during an actual request. ex) "Authorization", "Cache-Control", "Content-Type"
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
