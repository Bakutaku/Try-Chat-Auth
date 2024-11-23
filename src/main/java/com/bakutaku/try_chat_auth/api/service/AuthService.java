package com.bakutaku.try_chat_auth.api.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

/**
 * 認証関係の処理をまとめたサービス
 */
@Service
public class AuthService {

  /**
   * ユーザIDを取得する
   * 
   * @return ユーザID
   */
  public Optional<String> getUserId(Authentication auth) {

    // 結果格納用
    String userID = null;

    // ユーザ情報の取り出し
    Object principal = auth.getPrincipal();

    // ユーザの詳細情報か
    if (principal instanceof Jwt) {
      // 型変換
      Jwt jwt = (Jwt) principal;

      userID = jwt.getClaimAsString("sub");

      System.out.println(userID);
    }

    // 結果を返す
    return Optional.ofNullable(userID);
  }

}
