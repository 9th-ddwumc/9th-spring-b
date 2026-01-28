package com.example.umc9th.domain.uuid.repository;

import com.example.umc9th.domain.uuid.entity.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UuidRepository extends JpaRepository<Uuid, Long> {
}

