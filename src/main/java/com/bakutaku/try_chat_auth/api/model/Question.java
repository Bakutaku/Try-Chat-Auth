package com.bakutaku.try_chat_auth.api.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 質問テーブル
 */
@Data // getterやsetterの追加
@Builder // コンストラクタを使用しないインスタンス生成するメソッドの追加
@NoArgsConstructor // 引数を必要としないコンストラクタ追加
@AllArgsConstructor // 属性すべてを必要とするコンストラクタ
@Entity // データベースのデータ(おまじないみたいなもの)
@EntityListeners(AuditingEntityListener.class) // 日付の自動入力用
@Table(name = "question") // テーブル(おまじないみたいなもの)
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String userId; // 投稿者

  private String title; // タイトル

  private String explanation; // 説明

  @Lob
  @Column(columnDefinition = "TEXT")
  private String nodes; // ノード

  @Lob
  @Column(columnDefinition = "TEXT")
  private String edges; // エッジ

  @CreatedDate
  private Date createAt; // 投稿日

  @LastModifiedDate
  private Date updateAt; // 更新日

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  private List<Answer> answer;

}