
SET FOREIGN_KEY_CHECKS = 0;

-- 초기화
TRUNCATE TABLE `review`;
TRUNCATE TABLE `store`;
TRUNCATE TABLE `member`;

-- 멤버
INSERT INTO `member`
(
    `id`,
    `provider`,
    `provider_id`,
    `name`,
    `nickname`,
    `gender`,
    `email`,
    `phone_number`,
    `profile_image_url`,
    `address`,
    `birth`,
    `status`,
    `inactive_date`,
    `created_at`,
    `updated_at`
)
VALUES
    (
        1,
        'KAKAO',              -- enum: LOCAL, KAKAO, NAVER, GOOGLE 중 하나
        'kakao-123',
        '홍길동',             -- 길이 3 충족 (엔티티 제약: length = 3, NOT NULL)
        'yunni',              -- NOT NULL
        'NONE',               -- enum: MALE, FEMALE, NONE (기본 NONE)
        'me@test.com',
        '010-0000-0000',
        NULL,
        'Seoul',              -- NOT NULL
        '2001-01-01',         -- LocalDate (YYYY-MM-DD), NOT NULL
        NULL,                 -- status (nullable)
        NULL,                 -- inactive_date (nullable)
        NOW(),
        NOW()
    );


-- 가게
INSERT INTO `store`
(`id`, `name`, `created_at`, `updated_at`)
VALUES
    (10, '반이학생마라탕마라반', NOW(), NOW()),
    (11, '마라천국',          NOW(), NOW());


-- 리뷰 (reviewer_id = member.id)
INSERT INTO `review`
(`id`, `store_id`, `reviewer_id`, `rating`, `content`, `created_at`, `updated_at`)
VALUES
    (100, 10, 1, 4.5, '분위기가 좋고 넓어요.', NOW(), NOW()),
    (101, 10, 1, 3.8, '단체 모임도 좋아요.', NOW(), NOW()),
    (102, 11, 1, 5.0, '최고였어요!',        NOW(), NOW());

SET FOREIGN_KEY_CHECKS = 1;
