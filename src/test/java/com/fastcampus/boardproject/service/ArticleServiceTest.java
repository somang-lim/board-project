package com.fastcampus.boardproject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import com.fastcampus.boardproject.domain.Article;
import com.fastcampus.boardproject.domain.type.SearchType;
import com.fastcampus.boardproject.dto.ArticleDto;
import com.fastcampus.boardproject.dto.ArticleUpdateDto;
import com.fastcampus.boardproject.repository.ArticleRepository;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

	@InjectMocks private ArticleService sut;

	@Mock private ArticleRepository articleRepository;

	@DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
	@Test
	void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
		// Given

		// When
		Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword");

		// Then
		assertThat(articles).isNotNull();
	}

	@DisplayName("게시글을 조회하면, 게시글이 반환한다.")
	@Test
	void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
		// Given

		// When
		ArticleDto articles = sut.searchArticle(1L);

		// Then
		assertThat(articles).isNotNull();
	}

	@DisplayName("게시글 정보를 입력하면, 게시글이 생성된다.")
	@Test
	void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
		// Given
		given(articleRepository.save(any(Article.class))).willReturn(null);

		// When
		sut.saveArticle(ArticleDto.of(LocalDateTime.now(), "imhope", "title", "content", "#java"));

		// Then
		then(articleRepository).should().save(any(Article.class));
	}

	@DisplayName("게시글의 ID와 수정 정보를 입력하면, 게시글을 수정한다.")
	@Test
	void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle() {
		// Given
		given(articleRepository.save(any(Article.class))).willReturn(null);

		// When
		sut.updateArticle(1L, ArticleUpdateDto.of("title", "content", "#java"));

		// Then
		then(articleRepository).should().save(any(Article.class));
	}

	@DisplayName("게시글의 ID를 입력하면, 게시글을 삭제한다.")
	@Test
	void givenArticleId_whenDeletingArticle_thenDeletesArticle() {
		// Given
		willDoNothing().given(articleRepository).delete(any(Article.class));

		// When
		sut.deleteArticle(1L);

		// Then
		then(articleRepository).should().delete(any(Article.class));
	}

}
