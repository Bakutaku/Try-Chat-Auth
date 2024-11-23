package com.bakutaku.try_chat_auth.api.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bakutaku.try_chat_auth.api.bean.form.request.QuestionPostRequest;
import com.bakutaku.try_chat_auth.api.model.Answer;
import com.bakutaku.try_chat_auth.api.model.Question;
import com.bakutaku.try_chat_auth.api.repository.AnswerRepository;
import com.bakutaku.try_chat_auth.api.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

/**
 * TryChatControllerのService
 */
@Service
@RequiredArgsConstructor // インスタンス自動生成
public class TryChatService {

  // データベース
  private final QuestionRepository questionRep; // 質問

  // 認証関係の処理
  private final AuthService authService;

  /**
   * 質問投稿
   */
  public Optional<Question> post(QuestionPostRequest req, Authentication auth) {

    // 保存するデータ作成
    var answer = new Answer();
    var question = Question.builder()
        .title(req.getTitle()) // タイトル
        .explanation(req.getExplanation()) // 説明
        .nodes(req.getNodes()) // 内容(JSON)
        .answer(answer)
        .userId(authService.getUserId(auth).get())
        .build();

    answer.setQuestion(question);
    answer.setNodes(req.getNodes());

    // 保存
    Question rs = questionRep.save(question);

    // 結果を返す
    return Optional.ofNullable(rs);
  }

}
