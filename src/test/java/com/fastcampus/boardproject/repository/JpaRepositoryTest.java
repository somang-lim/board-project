package com.fastcampus.boardproject.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

import com.fastcampus.boardproject.domain.Article;
import com.fastcampus.boardproject.domain.UserAccount;

@ActiveProfiles("testdb")
@DisplayName("JPA 연결 테스트")
@Import(JpaRepositoryTest.TestJpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

	private final ArticleRepository articleRepository;
	private final ArticleCommentRepository articleCommentRepository;
	private final UserAccountRepository userAccountRepository;

	JpaRepositoryTest(
		@Autowired ArticleRepository articleRepository,
		@Autowired ArticleCommentRepository articleCommentRepository,
		@Autowired UserAccountRepository userAccountRepository
	) {
		this.articleRepository = articleRepository;
		this.articleCommentRepository = articleCommentRepository;
		this.userAccountRepository = userAccountRepository;
	}


	@DisplayName("select 테스트")
	@Test
	void givenTestData_whenSelecting_thenWorksFine() {
		// Given

		// When
		List<Article> articles = articleRepository.findAll();

		// Then
		assertThat(articles)
			.isNotNull()
			.hasSize(123);
	}

	@DisplayName("insert 테스트")
	@Test
	void givenTestData_whenInserting_thenWorksFine() {
		// Given
		long previousCount = articleRepository.count();
		UserAccount userAccount = userAccountRepository.save(UserAccount.of("newImhope", "pw", null, null, null));
		Article article = Article.of(userAccount, "new article", "new content", "#spring");

		// When
		Article savedArticle = articleRepository.save(article);

		// Then
		assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
	}

	@DisplayName("update 테스트")
	@Test
	void givenTestData_whenUpdating_thenWorksFine() {
		// Given
		Article article = articleRepository.findById(1L).orElseThrow();
		String updatedHashtag = "#springboot";
		article.setHashtag(updatedHashtag);

		// When
		Article savedArticle = articleRepository.saveAndFlush(article);

		// Then
		assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
	}

	@DisplayName("delete 테스트")
	@Test
	void givenTestData_whenDeleting_thenWorksFine() {
		// Given
		Article article = articleRepository.findById(1L).orElseThrow();
		long previousArticleCount = articleRepository.count();
		long previousArticleCommentCount = articleCommentRepository.count();
		int deletedCommentsSize = article.getArticleComments().size();

		// When
		articleRepository.delete(article);

		// Then
		assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
		assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);
	}


	@EnableJpaAuditing
	@TestConfiguration
	static class TestJpaConfig {
		@Bean
		public AuditorAware<String> auditorAware() {
			return () -> Optional.of("imhope");
		}
	}

}
