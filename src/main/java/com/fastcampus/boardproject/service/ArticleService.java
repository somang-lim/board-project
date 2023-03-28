package com.fastcampus.boardproject.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.boardproject.domain.type.SearchType;
import com.fastcampus.boardproject.dto.ArticleDto;
import com.fastcampus.boardproject.dto.ArticleUpdateDto;
import com.fastcampus.boardproject.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

	private final ArticleRepository articleRepository;

	@Transactional(readOnly = true)
	public Page<ArticleDto> searchArticles(SearchType type, String search_keyword) {
		return Page.empty();
	}

	@Transactional(readOnly = true)
	public ArticleDto searchArticle(long articleId) {
		return null;
	}

	public void saveArticle(ArticleDto dto) {
	}

	public void updateArticle(long articleId, ArticleUpdateDto dto) {
	}

	public void deleteArticle(long articleId) {
	}

}
