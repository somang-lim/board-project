package com.fastcampus.boardproject.repository.querydsl;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fastcampus.boardproject.domain.Article;

public interface ArticleRepositoryCustom{

	/**
	 * @deprecated 해시태그 도메인을 새로 만들었기 때문에 이 코드는 더 이상 사용하지 않는다.
	 * @see HashtagRepositoryCustom#findAllHashtagNames()
	 */
	@Deprecated
	List<String> findAllDistinctHashtags();

	Page<Article> findByHashtagNames(Collection<String> hashtagNames, Pageable pageable);


}
