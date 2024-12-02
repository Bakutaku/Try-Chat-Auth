package com.bakutaku.try_chat_auth.api.bean.form.response.data;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 回答一覧取得のレスポンス
 */
@Data // getterやsetterの追加
@Builder // コンストラクタを使用しないインスタンス生成するメソッドの追加
@NoArgsConstructor // 引数を必要としないコンストラクタ追加
@AllArgsConstructor // 属性すべてを必要とするコンストラクタ
public class AnswerListResponse {
  private UUID id; // 投稿ID
  private String userId; // ユーザID
  private String nodes; // ノード
  private String edges; // エッジ
  private Date createAt; // 投稿日
  private Date updateAt; // 更新日
}
