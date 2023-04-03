package com.fastcampus.boardproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fastcampus.boardproject.dto.UserAccountDto;
import com.fastcampus.boardproject.dto.request.ArticleCommentRequest;
import com.fastcampus.boardproject.service.ArticleCommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {

	private final ArticleCommentService articleCommentService;

	@PostMapping("/new")
	public String postNewArticleComment(ArticleCommentRequest articleCommentRequest) {
		// TODO : 인증 정보를 추가해야 한다.
		articleCommentService.saveArticleComment(articleCommentRequest.toDto(UserAccountDto.of(
			"imhope", "qwerasdf", "imhope@mail.com", "imhope", "memo"
		)));

		return "redirect:/articles/" + articleCommentRequest.articleId();
	}

	@PostMapping("/{commentId}/delete")
	public String deleteArticleComment(@PathVariable Long commentId, Long articleId) {
		articleCommentService.deleteArticleComment(commentId);

		return "redirect:/articles/" + articleId;
	}

}
