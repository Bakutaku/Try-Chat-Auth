package com.bakutaku.try_chat_auth.api.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 回答テーブル
 */
@Data // getterやsetterの追加
@Builder // コンストラクタを使用しないインスタンス生成するメソッドの追加
@NoArgsConstructor // 引数を必要としないコンストラクタ追加
@AllArgsConstructor // 属性すべてを必要とするコンストラクタ
@Entity // データベースのデータ(おまじないみたいなもの)
@EntityListeners(AuditingEntityListener.class) // 日付の自動入力用
@Table(name = "answer") // テーブル(おまじないみたいなもの)
public class Answer {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String userId;

  @Lob
  @Column(columnDefinition = "TEXT")
  private String nodes; // ノード

  @Lob
  @Column(columnDefinition = "TEXT")
  private String edges; // エッジ

  @ManyToOne
  @JoinColumn(name = "question_id")
  private Question question;

  @CreatedDate
  private Date createAt; // 投稿日

  @LastModifiedDate
  private Date updateAt; // 更新日

}
