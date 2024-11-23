package com.bakutaku.try_chat_auth.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bakutaku.try_chat_auth.api.bean.form.request.QuestionPostRequest;
import com.bakutaku.try_chat_auth.api.model.Answer;
import com.bakutaku.try_chat_auth.api.model.Question;
import com.bakutaku.try_chat_auth.api.repository.QuestionRepository;

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
  public Optional<Question> post(QuestionPostRequest req, Authentication auth) {

    // 保存するデータ作成
    var answer = new Answer();
    var question = Question.builder()
        .title(req.getTitle()) // タイトル
        .explanation(req.getExplanation()) // 説明
        .nodes(req.getNodes()) // 内容(JSON)
        .edges(req.getEdges()) // 内容(JSON)
        .answer(answer)
        .userId(authService.getUserId(auth).get())
        .build();

    answer.setQuestion(question);
    answer.setNodes(req.getNodes());
    answer.setEdges(req.getEdges());

    // 保存
    Question rs = questionRep.save(question);

    // 結果を返す
    return Optional.ofNullable(rs);
  }

  /**
   * 質問一覧取得
   */
  public Optional<List<Question>> list() {

    // データ取得
    List<Question> items = questionRep.findAll();

    // 結果を返す
    return Optional.ofNullable(items);

  }

  public Optional<Question> item(UUID id) {

    // データ取得 & 結果を返す
    return questionRep.findById(id);
  }

}
