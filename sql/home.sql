SELECT
    mission.id AS mission_id,
    mission.store_id,
    store.name AS store_name,
    store.area_id,
    area.name AS area_name,
    mission.content AS mission_content,
    mission.reward_point,
    mission.end_day,
    mission_member.status AS mission_status,
    -- 해당 지역에서 성공한 미션 개수
    (SELECT COUNT(*)
     FROM mission_member
              INNER JOIN mission AS completed_mission ON mission_member.mission_id = completed_mission.id
              INNER JOIN store AS sub_store ON completed_mission.store_id = sub_store.id
     WHERE mission_member.member_id = ?
       AND mission_member.status = 'COMPLETED'
       AND sub_store.area_id = store.area_id) AS area_completed_count,
    -- 커서 값 (마감일 얼마 남지 않은 순으로 정렬)
    CONCAT(mission.end_day, '_', mission.id) AS cursor_value
FROM mission
         INNER JOIN store ON mission.store_id = store.id
         INNER JOIN area ON store.area_id = area.id
         LEFT JOIN mission_member ON mission.id = mission_member.mission_id
    AND mission_member.member_id = ?  -- 로그인된 회원 ID
         INNER JOIN member ON member.id = ?  -- 회원 정보 조인
WHERE area.name = '안암동'  -- 지역명
  AND mission.end_day >= CURDATE()
  AND mission_member.status = 'IN_PROGRESS'  -- 진행중인 미션만
  AND CONCAT(mission.end_day, '_', mission.id) > ?
ORDER BY mission.end_day ASC, mission.id ASC
    LIMIT 5;