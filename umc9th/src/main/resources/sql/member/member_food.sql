CREATE TABLE member_food (
                             member_food_id BIGINT NOT NULL AUTO_INCREMENT,
                             member_id BIGINT NOT NULL,
                             food_id BIGINT NOT NULL,
                             PRIMARY KEY (member_id, food_id),
                             FOREIGN KEY (member_id) REFERENCES member(member_id),
                             FOREIGN KEY (food_id) REFERENCES food(food_id),
                             UNIQUE KEY (member_food_id)
                         );
