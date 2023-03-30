package com.fastcampus.boardproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.boardproject.dto.ArticleCommentDto;
import com.fastcampus.boardproject.dto.ArticleDto;
import com.fastcampus.boardproject.repository.ArticleCommentRepository;
import com.fastcampus.boardproject.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

	private final ArticleRepository articleRepository;
	private final ArticleCommentRepository articleCommentRepository;

	@Transactional(readOnly = true)
	public List<ArticleCommentDto> searchArticleComments(long articleId) {
		return List.of();
	}

	public void saveArticleComment(ArticleCommentDto dto) {
	}

	public void updateArticleComment(ArticleCommentDto dto) {
	}

	public void deleteArticleComment(Long articleCommentId) {
	}

}
