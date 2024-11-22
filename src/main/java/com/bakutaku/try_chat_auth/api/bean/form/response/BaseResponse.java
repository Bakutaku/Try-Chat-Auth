package com.bakutaku.try_chat_auth.api.bean.form.response;

import com.bakutaku.try_chat_auth.api.enums.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レスポンスのベース
 */
@Data // getterやsetterの追加
@NoArgsConstructor // 引数を必要としないコンストラクタ追加
@AllArgsConstructor // 属性すべてを必要とするコンストラクタ
public class BaseResponse {
  private ResponseStatus status; // ステータス
  private String message; // メッセージ
}
