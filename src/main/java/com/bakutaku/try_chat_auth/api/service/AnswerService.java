package com.bakutaku.try_chat_auth.api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bakutaku.try_chat_auth.api.model.Answer;
import com.bakutaku.try_chat_auth.api.repository.AnswerRepository;

import lombok.RequiredArgsConstructor;

/**
 * AnswerControllerのサービス
 */
@Service
@RequiredArgsConstructor // インスタンス自動生成
public class AnswerService {

  // データベース
  private final AnswerRepository answerRep;

  /**
   * 回答詳細取得
   */
  public Optional<Answer> item(UUID id) {
    // 取得 & 返す
    return answerRep.findById(id);
  }

}
