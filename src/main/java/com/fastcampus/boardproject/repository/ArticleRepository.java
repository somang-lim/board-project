package com.fastcampus.boardproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fastcampus.boardproject.domain.Article;
import com.fastcampus.boardproject.domain.QArticle;
import com.fastcampus.boardproject.repository.querydsl.ArticleRepositoryCustom;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;

@RepositoryRestResource
public interface ArticleRepository extends
	JpaRepository<Article, Long>,
	ArticleRepositoryCustom,
	QuerydslPredicateExecutor<Article>,
	QuerydslBinderCustomizer<QArticle>
{

	Page<Article> findByTitleContaining(String title, Pageable pageable);
	Page<Article> findByContentContaining(String content, Pageable pageable);
	Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);
	Page<Article> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);

	void deleteByIdAndUserAccount_UserId(Long articleId, String userId);

	@Override
	default void customize(QuerydslBindings bindings, QArticle root) {
		bindings.excludeUnlistedProperties(true);
		bindings.including(root.title, root.content, root.hashtags, root.createdAt, root.createdBy);
		bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%${v}%
		bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%${v}%
		bindings.bind(root.hashtags.any().hashtagName).first(StringExpression::containsIgnoreCase); // like '%${v}%
		bindings.bind(root.createdAt).first(DateTimeExpression::eq); // like '%${v}%
		bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like '%${v}%
	}

}
