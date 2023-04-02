package com.fastcampus.boardproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.boardproject.domain.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
