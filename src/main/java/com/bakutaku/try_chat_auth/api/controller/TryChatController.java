package com.bakutaku.try_chat_auth.api.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.bakutaku.try_chat_auth.api.bean.form.request.QuestionPostRequest;
import com.bakutaku.try_chat_auth.api.bean.form.response.ErrorResponse;
import com.bakutaku.try_chat_auth.api.bean.form.response.SuccessResponse;
import com.bakutaku.try_chat_auth.api.bean.form.response.data.PostResponse;
import com.bakutaku.try_chat_auth.api.model.Question;
import com.bakutaku.try_chat_auth.api.service.TryChatService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Try-Chatが使用するコントローラ
 */
@RestController // おまじないみたいなもの
@RequestMapping("/api/question") // URLの指定
@RequiredArgsConstructor // インスタンス自動生成
public class TryChatController {

  // 処理が記述されたサービス
  private final TryChatService service;

  /**
   * 質問の投稿
   */
  @PostMapping()
  public ResponseEntity<?> post(@Valid @RequestBody QuestionPostRequest req, Authentication auth) {

    // 処理を実行
    Optional<Question> rs = service.post(req, auth);

    // 結果が入っていない場合
    if (rs.isEmpty()) {
      // エラーを返す
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse("投稿に失敗しました", HttpStatus.BAD_REQUEST));
    }

    // 結果取り出し
    Question data = rs.get();

    // 結果を返す
    return ResponseEntity.ok(new SuccessResponse<>(
        PostResponse.builder()
            .id(data.getId())
            .title(data.getTitle())
            .time(data.getCreateAt())
            .build()));
  }

}
