package com.workbook.umc9th1.review.repository;

import com.workbook.umc9th1.review.domain.Review;
import com.workbook.umc9th1.store.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl  {
    boolean existsByStore_IdAndReviewer_Id(Long storeId, Long reviewerId);

    List<Review> findByStore_IdOrderByIdDesc(Long storeId);

    @Query("select avg(r.rating) from Review r where r.store.id = :storeId")
    Double getAverageRatingByStoreId(Long storeId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = """
        INSERT INTO review (member_id, store_id, content, rating, created_at, updated_at) 
        VALUES (:memberId, :storeId, :content, :rating, now(), now())
        """, nativeQuery = true)
    int insertReviewNative(Long memberId, Long storeId, String content, Double rating);

    Page<Review> findAllByStore(Store store, Pageable pageable);

}
