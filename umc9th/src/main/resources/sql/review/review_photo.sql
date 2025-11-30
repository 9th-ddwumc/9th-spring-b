CREATE TABLE review_photo (
                             review_photo_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             photo_url VARCHAR(255) NOT NULL,
                             review_id BIGINT NOT NULL,
                             FOREIGN KEY (review_id) REFERENCES review(review_id)
);
