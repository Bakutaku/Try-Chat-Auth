package com.bakutaku.try_chat_auth.api.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * レスポンスステータス
 */
@Getter
@AllArgsConstructor
public enum ResponseStatus {

  SUCCESS("success"),
  ERROR("error");

  private String status; // ステータス

  @Override
  public String toString() {
    return status;
  }

  @JsonValue
  public String getStatus() {
    return status;
  }

}
