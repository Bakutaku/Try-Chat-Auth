package com.bakutaku.try_chat_auth.api.bean.form.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * リストのリクエスト用
 */
@Data // getterやsetterの追加
@Builder // コンストラクタを使用しないインスタンス生成するメソッドの追加
@NoArgsConstructor // 引数を必要としないコンストラクタ追加
@AllArgsConstructor // 属性すべてを必要とするコンストラクタ
public class ListRequest {

  private int page; // ページ
  private int size; // ページサイズ
}
