package com.bakutaku.try_chat_auth.api.bean.form.response.data;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getterやsetterの追加
@Builder // コンストラクタを使用しないインスタンス生成するメソッドの追加
@NoArgsConstructor // 引数を必要としないコンストラクタ追加
@AllArgsConstructor // 属性すべてを必要とするコンストラクタ
/**
 * 質問投稿のレスポンス用
 */
public class PostResponse {
  private UUID id; // 質問ID
  private String title; // タイトル
  private Date time; // 時間
}
