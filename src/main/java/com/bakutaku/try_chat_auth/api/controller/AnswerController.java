package com.bakutaku.try_chat_auth.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakutaku.try_chat_auth.api.bean.form.request.AnswerPostRequest;
import com.bakutaku.try_chat_auth.api.bean.form.response.ErrorResponse;
import com.bakutaku.try_chat_auth.api.bean.form.response.SuccessResponse;
import com.bakutaku.try_chat_auth.api.bean.form.response.data.AnswerItemResponse;
import com.bakutaku.try_chat_auth.api.bean.form.response.data.AnswerListResponse;
import com.bakutaku.try_chat_auth.api.bean.form.response.data.AnswerPostResponse;
import com.bakutaku.try_chat_auth.api.model.Answer;
import com.bakutaku.try_chat_auth.api.service.AnswerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Try-Chatで使用するコントローラ
 */
@RestController // おまじないみたいなもの
@RequestMapping("/api/answer") // URLの指定
@RequiredArgsConstructor // インスタンス自動生成
public class AnswerController {

  private final AnswerService service; // 処理をまとめたもの

  /**
   * 回答投稿
   * 
   * @param id 投稿ID
   */
  @PostMapping("/{id}")
  public ResponseEntity<?> post(@PathVariable UUID id, @Valid @RequestBody AnswerPostRequest req, Authentication auth) {

    // 処理を実行
    Optional<Answer> rs = service.post(id, req, auth);

    // 結果が入っていない場合
    if (rs.isEmpty()) {
      // エラーを返す
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse("投稿に失敗しました", HttpStatus.BAD_REQUEST));
    }

    // 結果取り出し
    Answer data = rs.get();

    // 結果を返す
    return ResponseEntity.ok(new SuccessResponse<>(
        AnswerPostResponse.builder()
            .id(data.getId())
            .question_id(data.getQuestion().getId().toString())
            .title(data.getQuestion().getTitle())
            .time(data.getCreateAt())
            .build()));
  }

  /**
   * 回答詳細閲覧
   * 
   * @param id 回答ID
   */
  @GetMapping("/item/{id}")
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
        .id(data.getId())
        .questionId(data.getQuestion().getId())
        .nodes(data.getNodes())
        .edges(data.getEdges())
        .createAt(data.getCreateAt())
        .updateAt(data.getUpdateAt())
        .build();

    // 結果を返す
    return ResponseEntity.ok(new SuccessResponse<>(item));
  }

  /**
   * 回答一覧取得
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> list(@PathVariable UUID id) {
    // 処理を実行
    List<Answer> items = service.list(id);

    // 変換
    List<AnswerListResponse> list = items.stream().map(
        (i) -> AnswerListResponse.builder()
            .id(i.getId())
            .userId(i.getUserId())
            .nodes(i.getNodes())
            .edges(i.getEdges())
            .createAt(i.getCreateAt())
            .updateAt(i.getUpdateAt())
            .build())
        .toList();

    // 結果を返す
    return ResponseEntity.ok(new SuccessResponse<>(list));
  }

}
