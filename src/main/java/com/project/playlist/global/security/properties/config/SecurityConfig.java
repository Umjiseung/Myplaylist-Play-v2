package com.project.playlist.global.security.properties.config;

import com.project.playlist.domain.member.enums.Authority;
import com.project.playlist.global.security.handler.JwtAccessDeniedHandler;
import com.project.playlist.global.security.handler.JwtAuthenticationEntryPoint;
import com.project.playlist.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF 설정 Disable
        http.csrf().disable()

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests()

                // auth
                .antMatchers(HttpMethod.POST, "/auth/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers(HttpMethod.PATCH, "/auth").permitAll()
                .antMatchers(HttpMethod.DELETE, "/auth").permitAll()

                // health
                .antMatchers(HttpMethod.GET, "/health").permitAll()

                // member
                .antMatchers(HttpMethod.GET, "/member/{studentId}").hasAnyAuthority(Authority.ROLE_USER.name(), Authority.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.GET, "/member").hasAnyAuthority(Authority.ROLE_USER.name(), Authority.ROLE_ADMIN.name())

                // playlist
                .antMatchers(HttpMethod.POST, "/playlist").hasAnyAuthority(Authority.ROLE_USER.name(), Authority.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.GET, "/playlist/{id}").hasAnyAuthority(Authority.ROLE_USER.name(), Authority.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.GET, "/playlist/{category}").hasAnyAuthority(Authority.ROLE_USER.name(), Authority.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.GET, "/playlist").hasAnyAuthority(Authority.ROLE_USER.name(), Authority.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.PATCH, "/playlist/{id}").hasAnyAuthority(Authority.ROLE_USER.name(), Authority.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/playlist/{id}").hasAnyAuthority(Authority.ROLE_USER.name(), Authority.ROLE_ADMIN.name())

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
