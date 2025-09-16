SELECT
    m.member_id,
    m.nickname,
    m.email,
    m.phone,
    p.amount
FROM umc_spring_b.point p
         LEFT JOIN umc_spring_b.member m -- 회원이 포인트가 없을수도 !!
              ON p.member_id = m.member_id
WHERE m.member_id = 1; -- 회원 아이디 가정
