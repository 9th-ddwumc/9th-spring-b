package com.example.jpa_practice.domain.member.repository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpa_practice.domain.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
        SELECT new com.example.jpa_practice.domain.member.dto.UserMypageDto(
            u.nickname,
            u.email,
            u.phoneNumber,
            u.point
        )
        FROM User u
        WHERE u.userId = :userId
    """)
    List<User> findByNameAndDeletedAtIsNull(String name);

    Optional<User> findByEmailAndDeletedAtIsNull(String email);
    boolean existsByEmailAndDeletedAtIsNull(String email);
    
    // 디버깅용: 이메일로 조회 (삭제 여부 무관)
    Optional<User> findByEmail(String email);

}
