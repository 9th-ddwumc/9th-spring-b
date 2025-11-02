package com.workbook.umc9th1.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.workbook.umc9th1.review.domain.QReview;
import com.workbook.umc9th1.review.dto.MyReviewDto;
import com.workbook.umc9th1.store.domain.QStore;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    public ReviewQueryDslImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Page<MyReviewDto> searchMyReviews(Predicate predicate, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview r = QReview.review;
        QStore s = QStore.store;

        List<MyReviewDto> content = queryFactory
                .select(Projections.constructor(
                        MyReviewDto.class,
                        r.id,
                        s.id,
                        s.name,
                        r.rating,
                        r.content,
                        r.createdAt
                ))
                .from(r)
                .join(r.store, s)
                .where(predicate)
                .orderBy(r.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // total count
        Long total = queryFactory
                .select(r.id.count())
                .from(r)
                .join(r.store, s)
                .where(predicate)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}
