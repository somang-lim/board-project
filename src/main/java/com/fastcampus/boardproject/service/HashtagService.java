package com.fastcampus.boardproject.service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.boardproject.domain.Hashtag;
import com.fastcampus.boardproject.repository.HashtagRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class HashtagService {

	private final HashtagRepository hashtagRepository;


	@Transactional(readOnly = true)
	public Set<Hashtag> findHashtagsByNames(Set<String> hashtagNames) {
		return new HashSet<>(hashtagRepository.findByHashtagNameIn(hashtagNames));
	}

	public Set<String> parseHashtagNames(String content) {
		// 1. content가 null일 경우에는 빈 값을 반환한다.
		if (content == null) {
			return Set.of();
		}

		// 2. 해시태그 정규식에 맞는 content를 찾아서 반환한다.
		Pattern pattern = Pattern.compile("#[\\w가-힣]+");
		Matcher matcher = pattern.matcher(content.strip());
		Set<String> result = new HashSet<>();

		while (matcher.find()) {
			result.add(matcher.group().replace("#", ""));
		}

		return Set.copyOf(result); // 기존 result를 불변하게 만들어서 반환한다.
	}

	public void deleteHashtagWithoutArticles(Long hashtagId) {
		Hashtag hashtag = hashtagRepository.getReferenceById(hashtagId);

		if (hashtag.getArticles().isEmpty()) {
			hashtagRepository.delete(hashtag);
		}
	}

}
