package com.bakutaku.try_chat_auth.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * エラーの種類
 */
@Getter
@AllArgsConstructor
public enum ErrorStatus {
  NOT_FUND("404"), // 見つからない
  UNAUTHORIZED("401"); // 認証に失敗 or 権限がない

  private String status; // ステータス
}
