package com.bakutaku.try_chat_auth.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakutaku.try_chat_auth.api.bean.form.request.QuestionPostRequest;
import com.bakutaku.try_chat_auth.api.bean.form.response.SuccessResponse;
import com.bakutaku.try_chat_auth.api.service.TryChatService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

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
  @PostMapping("/post")
  public ResponseEntity<?> upload(@ModelAttribute QuestionPostRequest req) {

    System.out.println(req.getTitle());

    return ResponseEntity.ok(new SuccessResponse<String>("Test"));
  }

}
