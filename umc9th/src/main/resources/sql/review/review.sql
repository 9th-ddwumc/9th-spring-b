CREATE TABLE review (
                       review_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       content VARCHAR(255) NOT NULL,
                       createdAt DATETIME NOT NULL,
                       star FLOAT NOT NULL DEFAULT 0,
                       store_id BIGINT NOT NULL,
                       member_id BIGINT NOT NULL,
                       updatedAt DATETIME,
                       FOREIGN KEY (member_id) REFERENCES member(member_id),
                       FOREIGN KEY (store_id) REFERENCES store(store_id)
);
