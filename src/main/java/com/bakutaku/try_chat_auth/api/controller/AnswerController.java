package com.bakutaku.try_chat_auth.api.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakutaku.try_chat_auth.api.bean.form.response.ErrorResponse;
import com.bakutaku.try_chat_auth.api.bean.form.response.SuccessResponse;
import com.bakutaku.try_chat_auth.api.bean.form.response.data.AnswerItemResponse;
import com.bakutaku.try_chat_auth.api.model.Answer;
import com.bakutaku.try_chat_auth.api.service.AnswerService;

import lombok.RequiredArgsConstructor;

/**
 * Try-Chatで使用するコントローラ
 */
@RestController // おまじないみたいなもの
@RequestMapping("/api/answer") // URLの指定
@RequiredArgsConstructor // インスタンス自動生成
public class AnswerController {

  private final AnswerService service; // 処理をまとめたもの

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable UUID id) {
    return ResponseEntity.ok("");
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> item(@PathVariable UUID id) {
    // 処理を実行
    Optional<Answer> rs = service.item(id);

    // 結果がなければ
    if (rs.isEmpty()) {
      // エラーを返す
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse("取得に失敗しました", HttpStatus.BAD_REQUEST));
    }

    // 結果取り出し
    Answer data = rs.get();

    // 変換
    AnswerItemResponse item = AnswerItemResponse.builder()
        .id(data.getQuestionId())
        .nodes(data.getNodes())
        .edges(data.getEdges())
        .build();

    // 結果を返す
    return ResponseEntity.ok(new SuccessResponse<>(item));
  }

}
