USE umc_spring_b;

CREATE TABLE umc_spring_b.location (
                                       location_id BIGINT PRIMARY KEY,
                                       location_name VARCHAR(10)
);

CREATE TABLE umc_spring_b.market (
                                     market_id BIGINT PRIMARY KEY,
                                     name VARCHAR(6),
                                     category VARCHAR(20),
                                     address VARCHAR(30),
                                     phone VARCHAR(13),
                                     description VARCHAR(100),
                                     created_at DATETIME(6),
                                     deleted_at DATETIME(6),
                                     latitude DECIMAL(9,6),
                                     longitude DECIMAL(9,6),
                                     location_id BIGINT,
                                     FOREIGN KEY (location_id) REFERENCES location(location_id)
);

CREATE TABLE umc_spring_b.member (
                                     member_id BIGINT PRIMARY KEY,
                                     name VARCHAR(6),
                                     nickname VARCHAR(7),
                                     gender BIT,
                                     email VARCHAR(20),
                                     phone VARCHAR(13),
                                     birth VARCHAR(10),
                                     address VARCHAR(30),
                                     social_type VARCHAR(3),
                                     created_at DATETIME(6),
                                     deleted_at DATETIME(6),
                                     status VARCHAR(15),
                                     inactive_date VARCHAR(10),
                                     detail_address VARCHAR(15),
                                     social_uid VARCHAR(20)
);

CREATE TABLE umc_spring_b.mission (
                                      mission_id BIGINT PRIMARY KEY,
                                      deadline DATETIME(6),
                                      conditional VARCHAR(50),
                                      point INT,
                                      created_at DATETIME(6),
                                      deleted_at DATETIME(6),
                                      market_id BIGINT,
                                      FOREIGN KEY (market_id) REFERENCES market(market_id)
);

CREATE TABLE umc_spring_b.mission_member
(
    missionmember_id BIGINT PRIMARY KEY,
    complete         BIT,
    member_id        BIGINT,
    mission_id       BIGINT,
    FOREIGN KEY (member_id) REFERENCES member (member_id)
);

CREATE TABLE umc_spring_b.foodtype (
                                       foodtype_id BIGINT PRIMARY KEY,
                                       foodtype VARCHAR(10)
);

CREATE TABLE umc_spring_b.member_foodtype (
                                              member_foodtype_id BIGINT PRIMARY KEY,
                                              member_id BIGINT,
                                              foodtype_id BIGINT,
                                              FOREIGN KEY (member_id) REFERENCES member(member_id),
                                              FOREIGN KEY (foodtype_id) REFERENCES foodtype(foodtype_id)
);

CREATE TABLE umc_spring_b.review (
                                     review_id BIGINT PRIMARY KEY,
                                     rating INT,
                                     img VARCHAR(200),
                                     description VARCHAR(255),
                                     created_at DATETIME(6),
                                     deleted_at DATETIME(6),
                                     member_id BIGINT,
                                     market_id2 BIGINT,
                                     FOREIGN KEY (member_id) REFERENCES member(member_id),
                                     FOREIGN KEY (market_id2) REFERENCES market(market_id)
);

CREATE TABLE umc_spring_b.reviewreply (
                                          replay_id BIGINT PRIMARY KEY,
                                          content VARCHAR(40),
                                          review_id BIGINT,
                                          FOREIGN KEY (review_id) REFERENCES review(review_id)
);

CREATE TABLE umc_spring_b.customerservice (
                                              customerservice_id BIGINT PRIMARY KEY,
                                              title VARCHAR(20),
                                              content VARCHAR(255),
                                              img VARCHAR(200),
                                              created_at DATETIME(6),
                                              deleted_at DATETIME(6),
                                              member_id BIGINT,
                                              FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE umc_spring_b.form (
                                   form_id BIGINT PRIMARY KEY,
                                   form_name VARCHAR(10)
);

CREATE TABLE umc_spring_b.memberform (
                                         user_form_id BIGINT PRIMARY KEY,
                                         member_id BIGINT,
                                         form_id BIGINT,
                                         FOREIGN KEY (member_id) REFERENCES member(member_id),
                                         FOREIGN KEY (form_id) REFERENCES form(form_id)
);

CREATE TABLE umc_spring_b.point (
                                    point_id BIGINT PRIMARY KEY,
                                    amount BIGINT,
                                    created_at DATETIME(6),
                                    deleted_at DATETIME(6),
                                    member_id BIGINT,
                                    FOREIGN KEY (member_id) REFERENCES member(member_id)
);

