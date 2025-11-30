CREATE TABLE member (

                        member_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,

                        name VARCHAR(10) NOT NULL,

                        gender ENUM('MALE', 'FEMALE', 'NONE') NOT NULL DEFAULT 'NONE',

                        birth DATE,

                        address VARCHAR(255) NOT NULL,

                        detail_address VARCHAR(255) NOT NULL,

                        social_uid VARCHAR(255),

                        social_type ENUM('KAKAO', 'GOOGLE', 'NAVER'),

                        point INT NOT NULL DEFAULT 0,

                        email VARCHAR(255) NOT NULL UNIQUE,

                        phone_number VARCHAR(255) NOT NULL,

                        password VARCHAR(255) NOT NULL,

                        nickname VARCHAR(50) NOT NULL UNIQUE,

                        createdAt DATETIME NOT NULL,

                        updatedAt DATETIME
);

