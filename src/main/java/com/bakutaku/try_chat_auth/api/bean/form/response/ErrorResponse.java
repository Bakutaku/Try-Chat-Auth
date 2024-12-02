package com.bakutaku.try_chat_auth.api.bean.form.response;

import org.springframework.http.HttpStatus;

import com.bakutaku.try_chat_auth.api.enums.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // getterやsetterの追加
@EqualsAndHashCode(callSuper = true) // 親クラスのequals/hashCodeを呼び出す
public class ErrorResponse extends BaseResponse {
  private HttpStatus error; // エラーコード

  public ErrorResponse(String message, HttpStatus error) {
    super(ResponseStatus.ERROR, message);
    this.error = error;
  }
}
