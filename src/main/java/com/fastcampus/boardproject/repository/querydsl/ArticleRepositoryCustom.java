package com.fastcampus.boardproject.repository.querydsl;

import java.util.List;

public interface ArticleRepositoryCustom{
	List<String> findAllDistinctHashtags();

}
