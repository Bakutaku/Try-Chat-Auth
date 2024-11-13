package com.bakutaku.try_chat_auth.security;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

/**
 * JWTトークンの変換(Spring Securityで扱えるように変換)
 */
@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

  @Override
  public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
    Collection<GrantedAuthority> authorities = Stream
        .concat(
            jwtGrantedAuthoritiesConverter.convert(jwt).stream(), // 一般的な権限?
            extractClientRoles(jwt).stream()) // JWT内のロール
        .collect(Collectors.toSet());

    return new JwtAuthenticationToken(jwt, authorities);
  }

  /**
   * JWTからロールを抽出するメソッド
   */
  @SuppressWarnings("unchecked") // 警告を消すアノテーション(型変換のやつ)
  private Collection<? extends GrantedAuthority> extractClientRoles(Jwt jwt) {
    // よくわからないがKeycloakが発行したJWTトークンを解読し必要な情報を取り出しているみたい
    Map<String, Object> resourceAccess;
    Map<String, Object> clientConfig;
    Collection<String> clientRoles;

    Object rowResourceAccess = jwt.getClaim("resource_access");
    if (!(rowResourceAccess instanceof Map))
      return Set.of();

    resourceAccess = (Map<String, Object>) rowResourceAccess;

    Object rawClientConfig = resourceAccess.get("test_app_client");

    if (!(rawClientConfig instanceof Map))
      return Set.of();

    clientConfig = (Map<String, Object>) rawClientConfig;

    Object rawClientRoles = clientConfig.get("roles");

    if (!(rawClientRoles instanceof Collection))
      return Set.of();

    clientRoles = (Collection<String>) rawClientRoles;

    return clientRoles
        .stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // Spring Securityが要求する形に変換
        .collect(Collectors.toSet());
  }

}
