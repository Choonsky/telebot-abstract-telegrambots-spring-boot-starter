package com.nemirovsky.telegrambot.repository;

import com.nemirovsky.telegrambot.model.TelebotUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TelebotUserRepository extends JpaRepository<TelebotUser, Integer> {
    Optional<TelebotUser> findById(int id);
    Optional<TelebotUser> findByChatId(long chatId);
}
