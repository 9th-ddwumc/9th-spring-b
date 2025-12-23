CREATE TABLE member_term (
                             member_term_id BIGINT NOT NULL AUTO_INCREMENT,
                             member_id BIGINT NOT NULL,
                             term_id BIGINT NOT NULL,
                             PRIMARY KEY (member_id, term_id),
                             FOREIGN KEY (member_id) REFERENCES member(member_id),
                             FOREIGN KEY (term_id) REFERENCES term(term_id),
                             UNIQUE KEY (member_term_id)
);
