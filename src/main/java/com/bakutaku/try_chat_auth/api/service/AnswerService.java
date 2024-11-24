package com.bakutaku.try_chat_auth.api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bakutaku.try_chat_auth.api.bean.form.request.AnswerPostRequest;
import com.bakutaku.try_chat_auth.api.model.Answer;
import com.bakutaku.try_chat_auth.api.model.Question;
import com.bakutaku.try_chat_auth.api.repository.AnswerRepository;
import com.bakutaku.try_chat_auth.api.repository.QuestionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * AnswerControllerのサービス
 */
@Service
@RequiredArgsConstructor // インスタンス自動生成
public class AnswerService {

  // データベース
  private final QuestionRepository questionRep;
  private final AnswerRepository answerRep;

  // 認証関係の処理
  private final AuthService authService;

  /**
   * 回答詳細取得
   */
  public Optional<Answer> item(UUID id) {
    // 取得 & 返す
    return answerRep.findById(id);
  }

  /**
   * 投稿処理
   */
  @Transactional(rollbackOn = Exception.class) // 例外時にロールバックを行ってくれるもの
  public Optional<Answer> post(UUID id, AnswerPostRequest req, Authentication auth) {

    // 質問取得
    Optional<Question> question = questionRep.findById(id);

    // 取得できたか
    if (question.isEmpty()) {
      // できなかった場合
      return Optional.empty();
    }

    // ユーザID取得
    String userId = authService.getUserId(auth).get();

    // 保存するデータ作成
    var answer = Answer.builder()
        .nodes(req.getNodes())
        .edges(req.getEdges())
        .userId(userId)
        .question(question.get())
        .build();

    // 投稿者の回答を調べる
    Optional<Answer> existing = answerRep.findByUserIdAndQuestionId(userId, id);

    // 既に存在するか
    if (existing.isPresent()) {
      // 存在する場合
      // IDを設定する
      answer.setId(existing.get().getId());
      answer.setCreateAt(existing.get().getCreateAt());
    }

    // 保存 & 結果を返す
    return Optional.ofNullable(answerRep.save(answer));
  }

}
