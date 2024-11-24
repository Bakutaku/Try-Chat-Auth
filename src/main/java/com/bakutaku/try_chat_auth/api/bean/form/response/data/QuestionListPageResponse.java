package com.bakutaku.try_chat_auth.api.bean.form.response.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getterやsetterの追加
@Builder // コンストラクタを使用しないインスタンス生成するメソッドの追加
@NoArgsConstructor // 引数を必要としないコンストラクタ追加
@AllArgsConstructor // 属性すべてを必要とするコンストラクタ
public class QuestionListPageResponse {
  private List<QuestionListResponse> items; // 投稿一覧
  private int currentPage; // 現在のページ
  private int pageSize; // 1ページあたりのサイズ
  private int totalPage; // 全ページ数
  private long total; // 全データ数
}
