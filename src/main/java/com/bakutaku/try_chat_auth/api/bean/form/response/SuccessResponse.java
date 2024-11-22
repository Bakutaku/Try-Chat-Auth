package com.bakutaku.try_chat_auth.api.bean.form.response;

import com.bakutaku.try_chat_auth.api.enums.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // getterやsetterの追加
@EqualsAndHashCode(callSuper = true) // 親クラスのequals/hashCodeを呼び出す
public class SuccessResponse<T> extends BaseResponse {
  private T data; // データ

  /**
   * コンストラクタ
   * 
   * @param status  ステータス
   * @param message メッセージ
   * @param data    内容
   */
  public SuccessResponse(ResponseStatus status, String message, T data) {
    super(status, message);
    this.data = data;
  }

  /**
   * コンストラクタ
   * 
   * @param data 内容
   */
  public SuccessResponse(T data) {
    super(ResponseStatus.SUCCESS, "successfully");
    this.data = data;
  }
}
