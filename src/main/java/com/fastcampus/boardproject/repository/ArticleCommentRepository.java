package com.fastcampus.boardproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.boardproject.domain.ArticleComment;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
