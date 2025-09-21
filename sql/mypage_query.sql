SELECT
    member.id AS member_id,
    member.nickname,
    member.email,
    CASE
        WHEN member.phone_YN = 'Y' THEN member.phone_number
        ELSE '미인증'
        END AS phone_info,
    COALESCE(SUM(mission.reward_point), 0) AS total_point
FROM member member
         LEFT JOIN member_point member_point
                   ON member.id = member_point.member_id
         LEFT JOIN mission mission
                   ON member_point.mission_id = mission.id
WHERE member.id = 1
GROUP BY
    member.id,
    member.nickname,
    member.email,
    CASE WHEN member.phone_YN = 'Y' THEN member.phone_number ELSE '미인증' END;