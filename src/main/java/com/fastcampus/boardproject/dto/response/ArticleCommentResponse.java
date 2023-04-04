package com.fastcampus.boardproject.dto.response;

import java.time.LocalDateTime;

import com.fastcampus.boardproject.dto.ArticleCommentDto;

public record ArticleCommentResponse(
		Long id,
		String content,
		LocalDateTime createdAt,
		String email,
		String nickname,
		String userId
) {

	public static ArticleCommentResponse of(Long id, String content, LocalDateTime createdAt, String email, String nickname, String userId) {
		return new ArticleCommentResponse(id, content, createdAt, email, nickname, userId);
	}

	public static ArticleCommentResponse from(ArticleCommentDto dto) {
		String nickname = dto.userAccountDto().nickname();
		if (nickname == null || nickname.isBlank()) {
			nickname = dto.userAccountDto().userId();
		}

		return new ArticleCommentResponse(
			dto.id(),
			dto.content(),
			dto.createdAt(),
			dto.userAccountDto().email(),
			nickname,
			dto.userAccountDto().userId()
		);
	}

}
