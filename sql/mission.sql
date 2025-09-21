SELECT
    mission_member.id AS mission_member_id,
    mission.id AS mission_id,
    mission.store_id,
    store.name AS store_name,
    mission.content AS mission_content,
    mission.reward_point,
    mission_member.status,
    CASE
        WHEN mission_member.status = 'COMPLETED' THEN '성공'
        WHEN mission_member.status = 'IN_PROGRESS' THEN '진행중'
        END AS status_display,
    mission_member.requested_at,
    mission_member.completed_at,
    mission.created_at AS mission_created_at,
    CONCAT(mission_member.requested_at, '_', mission_member.id) AS cursor_value
FROM mission_member
         INNER JOIN mission
                    ON mission_member.mission_id = mission.id
         INNER JOIN store
                    ON mission.store_id = store.id
WHERE mission_member.member_id = ? -- 로그인된 회원 아이디
  AND CONCAT(mission_member.requested_at, '_', mission_member.id) < ? -- 이전 페이지의 마지막 cursor_value
ORDER BY mission_member.requested_at DESC, mission_member.id DESC
    LIMIT 5;
