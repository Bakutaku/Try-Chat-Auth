package com.bakutaku.try_chat_auth.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bakutaku.try_chat_auth.api.bean.form.request.QuestionPostRequest;

/**
 * TryChatControllerのService
 */
@Service
public class TryChatService {

  /**
   * 質問投稿
   */
  public Optional<?> post(QuestionPostRequest req) {

    return Optional.empty();
  }

}
