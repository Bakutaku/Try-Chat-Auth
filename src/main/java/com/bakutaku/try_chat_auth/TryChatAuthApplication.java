package com.bakutaku.try_chat_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 日付自動入力の有効化
@SpringBootApplication
public class TryChatAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(TryChatAuthApplication.class, args);
	}

}
