SELECT
    l.location_name,
    mm.missionmember_id,
    mm.complete,
    m.point,
    m.deadline,
    mk.market_id,
    mk.market_name

FROM umc_spring_b.mission m
         JOIN umc_spring_b.market mk
              ON m.market_id = mk.market_id
         JOIN umc_spring_b.location l
              ON mk.location_id = l.location_id
         LEFT JOIN umc_spring_b.mission_member mm
                   ON m.mission_id = mm.mission_id
                       AND mm.member_id = 1
WHERE (mm.complete IS NULL OR mm.complete = 0) -- 완료안한 미션 1/0
  AND l.location_name = '안암'
ORDER BY m.deadline ASC
    LIMIT 10 OFFSET 0;
