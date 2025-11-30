package com.workbook.umc9th1.store.repository;

import com.workbook.umc9th1.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByName(String name);
}
