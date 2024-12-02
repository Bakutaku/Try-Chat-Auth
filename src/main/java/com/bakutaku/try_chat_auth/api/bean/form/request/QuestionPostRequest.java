package com.bakutaku.try_chat_auth.api.bean.form.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 質問投稿のリクエスト
 */
@Data // getterやsetterの追加
@Builder // コンストラクタを使用しないインスタンス生成するメソッドの追加
@NoArgsConstructor // 引数を必要としないコンストラクタ追加
@AllArgsConstructor // 属性すべてを必要とするコンストラクタ
public class QuestionPostRequest {
  @NotNull
  private String title; // タイトル

  @NotNull
  private String explanation; // 説明

  @NotNull
  private String nodes; // ノード

  @NotNull
  private String edges; // エッジ
}
