package com.bakutaku.try_chat_auth.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.bakutaku.try_chat_auth.api.bean.form.request.QuestionPostRequest;
import com.bakutaku.try_chat_auth.api.bean.form.response.ErrorResponse;
import com.bakutaku.try_chat_auth.api.bean.form.response.SuccessResponse;
import com.bakutaku.try_chat_auth.api.bean.form.response.data.QuestionItemResponse;
import com.bakutaku.try_chat_auth.api.bean.form.response.data.QuestionListResponse;
import com.bakutaku.try_chat_auth.api.bean.form.response.data.QuestionPostResponse;
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
        QuestionPostResponse.builder()
            .id(data.getId())
            .title(data.getTitle())
            .time(data.getCreateAt())
            .build()));
  }

  @GetMapping()
  public ResponseEntity<?> list() {
    // 処理を実行
    Optional<List<Question>> rs = service.list();

    // 結果がなければ
    if (rs.isEmpty()) {
      // エラーを返す
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse("取得に失敗しました", HttpStatus.BAD_REQUEST));
    }

    // 結果取り出し
    List<Question> items = rs.get();

    // 変換
    List<QuestionListResponse> list = items.stream().map(
        (i) -> QuestionListResponse.builder()
            .id(i.getId())
            .title(i.getTitle())
            .explanation(i.getExplanation())
            .createAt(i.getCreateAt())
            .userID(i.getUserId())
            .build())
        .toList();

    return ResponseEntity.ok(new SuccessResponse<>(list));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> item(@PathVariable UUID id) {
    // 処理を実行
    Optional<Question> rs = service.item(id);

    // 結果がなければ
    if (rs.isEmpty()) {
      // エラーを返す
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse("取得に失敗しました", HttpStatus.BAD_REQUEST));
    }

    // 結果取り出し
    Question data = rs.get();

    // 変換
    QuestionItemResponse item = QuestionItemResponse.builder()
        .id(data.getId())
        .title(data.getTitle())
        .explanation(data.getExplanation())
        .createAt(data.getCreateAt())
        .updateAt(data.getUpdateAt())
        .userID(data.getUserId())
        .nodes(data.getNodes())
        .edges(data.getEdges())
        .build();

    // 結果を返す
    return ResponseEntity.ok(new SuccessResponse<>(item));
  }

}
