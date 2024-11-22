package com.bakutaku.try_chat_auth.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakutaku.try_chat_auth.api.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {

}
