package com.bakutaku.try_chat_auth.api.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakutaku.try_chat_auth.api.bean.form.response.TestUserResponse;

/**
 * 動作テスト用のコントローラー
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

  @GetMapping("user")
  public ResponseEntity<?> user(Authentication auth) {

    // レスポンス用(空の容器作成)
    Optional<TestUserResponse> res = Optional.empty();

    // ユーザ情報の取り出し
    Object principal = auth.getPrincipal();

    System.out.println(principal.getClass().getName());

    // ユーザの詳細情報なら
    if (principal instanceof Jwt) {
      // 型変換
      Jwt jwt = (Jwt) principal;

      // レスポンス生成
      res = Optional.of(TestUserResponse.builder()
          .message("リクエスト成功")
          .user(jwt.getClaimAsString("sub"))
          .time(new Date())
          .build());
    }

    // レスポンスがあるかどうか
    if (res.isEmpty()) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(TestUserResponse.builder()
          .message("ユーザ情報の取得に失敗しました")
          .time(new Date())
          .build());
    }

    // 結果を返す
    return ResponseEntity.ok(res.get());
  }

}
