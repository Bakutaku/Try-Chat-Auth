package com.bakutaku.try_chat_auth.api.bean.form.response.data;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 質問一覧取得のレスポンス
 */
@Data // getterやsetterの追加
@Builder // コンストラクタを使用しないインスタンス生成するメソッドの追加
@NoArgsConstructor // 引数を必要としないコンストラクタ追加
@AllArgsConstructor // 属性すべてを必要とするコンストラクタ
public class QuestionListResponse {
  private UUID id; // 質問ID
  private String title; // タイトル
  private String explanation; // 説明
  private String userID; // ユーザID
  private Date createAt; // 投稿日
}
