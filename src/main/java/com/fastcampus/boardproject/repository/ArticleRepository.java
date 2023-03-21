package com.fastcampus.boardproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.boardproject.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
