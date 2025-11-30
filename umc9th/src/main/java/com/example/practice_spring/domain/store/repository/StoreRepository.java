package com.example.practice_spring.domain.store.repository;

import com.example.practice_spring.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
