package com.fastcampus.boardproject.dto.request;

import java.util.Set;

import com.fastcampus.boardproject.dto.ArticleDto;
import com.fastcampus.boardproject.dto.HashtagDto;
import com.fastcampus.boardproject.dto.UserAccountDto;

public record ArticleRequest (
		String title,
		String content
) {

	public static ArticleRequest of(String title, String content) {
		return new ArticleRequest(title, content);
	}

	public ArticleDto toDto(UserAccountDto userAccountDto) {
		return toDto(userAccountDto, null);
	}

	public ArticleDto toDto(UserAccountDto userAccountDto, Set<HashtagDto> hashtagDtos) {
		return ArticleDto.of(
				userAccountDto,
				title,
				content,
				hashtagDtos
		);
	}

}
