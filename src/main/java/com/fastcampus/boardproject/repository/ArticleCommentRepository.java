package com.fastcampus.boardproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fastcampus.boardproject.domain.ArticleComment;
import com.fastcampus.boardproject.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;

@RepositoryRestResource
public interface ArticleCommentRepository extends
	JpaRepository<ArticleComment, Long>,
	QuerydslPredicateExecutor<ArticleComment>,
	QuerydslBinderCustomizer<QArticleComment>
{

	@Override
	default void customize(QuerydslBindings bindings, QArticleComment root) {
		bindings.excludeUnlistedProperties(true);
		bindings.including(root.content,root.createdAt, root.createdBy);
		bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%${v}%
		bindings.bind(root.createdAt).first(DateTimeExpression::eq); // like '%${v}%
		bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like '%${v}%
	}

}
