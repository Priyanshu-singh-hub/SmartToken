package com.smarttoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smarttoken.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
}