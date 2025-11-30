CREATE TABLE store
(
    store_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    manager_number BIGINT,
    detail_address VARCHAR(255),
    location_id BIGINT NOT NULL,
    FOREIGN KEY (location_id) REFERENCES location(location_id)
);
