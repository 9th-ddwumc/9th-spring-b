package com.workbook.umc9th1.global.config;

import com.workbook.umc9th1.global.auth.AuthenticationEntryPointImpl;
import com.workbook.umc9th1.global.auth.CustomUserDetailsService;
import com.workbook.umc9th1.global.auth.JwtAuthFilter;
import com.workbook.umc9th1.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity // spring security 활성화
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final String[] allowUris = {
            "/login",
            "/sign-up",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests // HTTP 요청에 대한 접근 제어
                        .requestMatchers(allowUris).permitAll() // 특정 URL 패턴에 대한 접근 권한을 설정. permitAll: 인증 없이 접근 가능한 경로 지정
                        .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN 역할을 가진 사용자만 접근 가능하도록 제한
                        .anyRequest().authenticated() // 그 외 모든 요청에 대해 인증을 요구
                )
                // 폼로그인 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
//                .formLogin(form -> form // 폼 기반 로그인
//                        .defaultSuccessUrl("/swagger-ui/index.html", true) // 로그인 성공 시 항상 swagger로 리다이렉트
//                        .permitAll()
//                )
                // JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .logout(logout -> logout // 로그아웃
                        .logoutUrl("/logout") // 해당 경로로 로그아웃 처리
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공 시 해당 경로로 리다이렉트
                        .permitAll()
                )
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint()))
                ;

        return http.build();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

}
