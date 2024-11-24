package com.bakutaku.try_chat_auth.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bakutaku.try_chat_auth.api.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
  /**
   * 投稿IDから取得する
   */
  public List<Answer> findByQuestionId(UUID questionId);

  /**
   * ユーザから取得する
   */
  public Optional<Answer> findByUserIdAndQuestionId(String userId, UUID questionId);
}
