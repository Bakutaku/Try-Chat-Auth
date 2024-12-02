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
public class AnswerPostResponse {
  private UUID id; // 回答ID
  private String question_id; // 質問ID
  private String title; // 質問のタイトル
  private Date time; // 時間
}
