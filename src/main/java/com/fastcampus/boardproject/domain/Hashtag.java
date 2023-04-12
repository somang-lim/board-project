package com.fastcampus.boardproject.domain;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
	@Index(columnList = "hashtagName", unique = true),
	@Index(columnList = "createdAt"),
	@Index(columnList = "createdBy")
})
@Entity
public class Hashtag extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ToString.Exclude
	@ManyToMany(mappedBy = "hashtags")
	private Set<Article> articles = new LinkedHashSet<>();

	@Setter @Column(nullable = false) private String hashtagName; // 해시태그 이름


	protected Hashtag() {}

	public Hashtag(String hashtagName) {
		this.hashtagName = hashtagName;
	}

	public static Hashtag of(String hashtagName) {
		return new Hashtag(hashtagName);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Hashtag)) return false;
		Hashtag hashtag = (Hashtag)o;
		return this.getId() != null && this.getId().equals(hashtag.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId());
	}
}
