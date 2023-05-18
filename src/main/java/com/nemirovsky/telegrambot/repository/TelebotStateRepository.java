package com.nemirovsky.telegrambot.repository;

import com.nemirovsky.telegrambot.model.TelebotState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TelebotStateRepository extends JpaRepository<TelebotState, Integer> {
    Optional<TelebotState> findById(int id);
}
