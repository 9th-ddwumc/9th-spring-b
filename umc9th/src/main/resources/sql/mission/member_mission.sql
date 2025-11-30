CREATE TABLE member_mission
(
    member_mission_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    is_complete BIT NOT NULL DEFAULT 0,
    mission_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    FOREIGN KEY (mission_id) REFERENCES mission(mission_id),
    FOREIGN KEY (member_id) REFERENCES member(member_id)
);
