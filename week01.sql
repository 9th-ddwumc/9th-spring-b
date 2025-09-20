-- 1. 리뷰 등록

INSERT INTO review (
    review_content,
    star,
    created_at,
    store_id,
    user_id
) VALUES (
    '음 너무 맛있어요 포인트도 얻고 맛있는 맛집도 알게 된 것 같아 너무나도 행복한 식사였습니다. 다음에도 또 올게요!!',
    5.0,
    NOW(),
    [가게 ID],
    [사용자 ID]
);

-- 2. 마이페이지
SELECT 
    user.nickname,
    user.email,
    user.phone_number,
    user.point
FROM 
    user
WHERE 
    user.user_id = [사용자 ID]


-- 3. 진행 중, 완료한 미션 모아서 보는 쿼리
SELECT
    M.mission_id,
    M.conditional,
    M.score,
    S.store_name,
    UM.mission_success
FROM
    user_mission AS UM
INNER JOIN
    mission AS M ON UM.mission_id = M.mission_id
INNER JOIN
    store AS S ON M.store_id = S.store_id
WHERE
    UM.user_id = [사용자 ID]
ORDER BY
    UM.created_at DESC
LIMIT 10 OFFSET 0;

--4. 홈화면
SELECT
    M.mission_id,
    M.conditional,
    M.score,
    S.store_name,
    S.category,
    L.location_name,
    M.mission_deadline
FROM
    mission AS M
INNER JOIN
    store AS S ON M.store_id = S.store_id
INNER JOIN
    location AS L ON S.location_id = L.location_id
LEFT JOIN
    user_mission AS UM ON M.mission_id = UM.mission_id AND UM.user_id = [사용자 ID]
WHERE
    L.location_name = '안암동' AND UM.mission_id IS NULL
ORDER BY
    M.created_at DESC
LIMIT 10 OFFSET 0;

