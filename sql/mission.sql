SELECT
    m.member_id,
    market.name,
    point.amount,
    mission.misson_id,
    mission.point,
    mission.deadlined,
    market.market_id,
    market.name,
    market.category,
    mission_member.complete,
FROM umc_spring_b.market market
         LEFT JOIN umc_spring_b.member m -- 회원이 포인트가 없을수도 !!
                   ON p.member_id = m.member_id
WHERE m.member_id = 1; -- 회원 아이디 가정
