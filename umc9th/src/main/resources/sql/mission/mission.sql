CREATE TABLE mission
(
    mission_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    deadline DATE,
    conditional VARCHAR(255) NOT NULL,
    point INT NOT NULL DEFAULT 0,
    createdAt DATETIME,
    store_id BIGINT NOT NULL,
    FOREIGN KEY (store_id) REFERENCES store(store_id)
);
