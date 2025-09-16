SELECT
    r.review_id,
    m.nickname,
    r.rating,
    r.created_at,
    r.description,
    rr.content AS reply_content
FROM umc_spring_b.review r
         JOIN umc_spring_b.member m
              ON r.member_id = m.member_id
         LEFT JOIN umc_spring_b.reviewreply rr
                   ON rr.review_id = r.review_id
WHERE r.review_id = 1;
