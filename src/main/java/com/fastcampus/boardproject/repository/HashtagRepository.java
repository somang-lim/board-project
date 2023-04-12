package com.fastcampus.boardproject.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fastcampus.boardproject.domain.Hashtag;
import com.fastcampus.boardproject.repository.querydsl.HashtagRepositoryCustom;

@RepositoryRestResource
public interface HashtagRepository extends
	JpaRepository<Hashtag, Long>,
	HashtagRepositoryCustom,
	QuerydslPredicateExecutor<Hashtag>
{
	Optional<Hashtag> findByHashtagName(String hashtagName);
	List<Hashtag> findByHashtagNameIn(Set<String> hashtagNames);
}
