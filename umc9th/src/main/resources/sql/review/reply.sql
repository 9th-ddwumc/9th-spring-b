CREATE TABLE reply (
                             reply_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             content VARCHAR(255) NOT NULL,
                             review_id BIGINT NOT NULL,
                             FOREIGN KEY (review_id) REFERENCES review(review_id),
                             UNIQUE KEY (reply_id),
                             member_id BIGINT NOT NULL,
                             FOREIGN KEY (member_id) REFERENCES member(member_id)
);
