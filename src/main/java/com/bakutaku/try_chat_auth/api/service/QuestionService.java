package com.bakutaku.try_chat_auth.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bakutaku.try_chat_auth.api.bean.form.request.ListRequest;
import com.bakutaku.try_chat_auth.api.bean.form.request.QuestionPostRequest;
import com.bakutaku.try_chat_auth.api.model.Answer;
import com.bakutaku.try_chat_auth.api.model.Question;
import com.bakutaku.try_chat_auth.api.repository.QuestionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * TryChatControllerのService
 */
@Service
@RequiredArgsConstructor // インスタンス自動生成
public class QuestionService {

  // データベース
  private final QuestionRepository questionRep; // 質問

  // 認証関係の処理
  private final AuthService authService;

  /**
   * 質問投稿
   */
  @Transactional(rollbackOn = Exception.class) // 例外時にロールバックを行ってくれるもの
  public Optional<Question> post(QuestionPostRequest req, Authentication auth) {

    // ユーザID取得
    String userId = authService.getUserId(auth).get();

    // 保存するデータ作成
    var answer = Answer.builder()
        .userId(userId)
        .nodes(req.getNodes())
        .edges(req.getEdges())
        .build();

    var question = Question.builder()
        .title(req.getTitle()) // タイトル
        .explanation(req.getExplanation()) // 説明
        .nodes(req.getNodes()) // 内容(JSON)
        .edges(req.getEdges()) // 内容(JSON)
        .userId(userId)
        .answer(List.of(answer))
        .build();

    answer.setQuestion(question);

    // 保存
    Question rs = questionRep.save(question);

    // 結果を返す
    return Optional.ofNullable(rs);
  }

  /**
   * 質問一覧取得
   */
  @Transactional(rollbackOn = Exception.class) // 例外時にロールバックを行ってくれるもの
  public Page<Question> list(ListRequest req) {

    // 取得範囲 & ソート設定
    Pageable pageable = PageRequest.of(req.getPage(), req.getSize(), Sort.by(Sort.Direction.DESC, "createAt"));

    // データ取得
    Page<Question> items = questionRep.findAll(pageable);

    // 結果を返す
    return items;

  }

  /**
   * 詳細取得
   * 
   * @param id 投稿ID
   */
  @Transactional(rollbackOn = Exception.class) // 例外時にロールバックを行ってくれるもの
  public Optional<Question> item(UUID id) {

    // データ取得 & 結果を返す
    return questionRep.findById(id);
  }

}
