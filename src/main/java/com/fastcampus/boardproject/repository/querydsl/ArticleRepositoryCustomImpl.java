package com.fastcampus.boardproject.repository.querydsl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.fastcampus.boardproject.domain.Article;
import com.fastcampus.boardproject.domain.QArticle;

public class ArticleRepositoryCustomImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {

	public ArticleRepositoryCustomImpl() {
		super(Article.class);
	}

	@Override
	public List<String> findAllDistinctHashtags() {
		QArticle article = QArticle.article;

		return from(article)
				.distinct()
				.select(article.hashtag)
				.where(article.hashtag.isNotNull())
				.fetch();
	}
}
