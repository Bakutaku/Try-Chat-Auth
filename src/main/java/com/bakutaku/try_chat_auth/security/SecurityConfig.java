package com.bakutaku.try_chat_auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration // コンフィグの定義
@EnableWebSecurity // セキュリティの有効化
@EnableMethodSecurity // ロール認証の有効化
@RequiredArgsConstructor // インスタンスの自動生成?(便利なやつ)
public class SecurityConfig {

  private final JwtAuthConverter jwtAuthConverter; // JWT変換用

  /**
   * Spring Securityの設定
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // セッションの無効化
        .csrf(csrf -> csrf.disable()) // CSRFを無効化
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/question/public").permitAll() // リストのみ権限不要
            .anyRequest().authenticated()) // 全てのリクエストに認証を求める
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))) // Keycloakとの連携設定
    ;

    // 設定内容を返す
    return http.build();
  }
}
