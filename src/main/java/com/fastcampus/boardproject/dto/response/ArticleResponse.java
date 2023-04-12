package com.fastcampus.boardproject.dto.response;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import com.fastcampus.boardproject.dto.ArticleDto;
import com.fastcampus.boardproject.dto.HashtagDto;

public record ArticleResponse(
		Long id,
		String title,
		String content,
		Set<String> hashtags,
		LocalDateTime createdAt,
		String email,
		String nickname
) {

	public static ArticleResponse of(Long id, String title, String content, Set<String> hashtags, LocalDateTime createdAt, String email, String nickname) {
		return new ArticleResponse(id, title, content, hashtags, createdAt, email, nickname);
	}

	public static ArticleResponse from(ArticleDto dto) {
		String nickname = dto.userAccountDto().nickname();
		if (nickname == null || nickname.isBlank()) {
			nickname = dto.userAccountDto().userId();
		}

		return new ArticleResponse(
			dto.id(),
			dto.title(),
			dto.content(),
			dto.hashtagDtos().stream()
					.map(HashtagDto::hashtagName)
					.collect(Collectors.toUnmodifiableSet())
			,
			dto.createdAt(),
			dto.userAccountDto().email(),
			nickname
		);
	}

}
