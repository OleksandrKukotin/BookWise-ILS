CREATE TABLE password_reset_tokens (
    id          SERIAL PRIMARY KEY,
    username    VARCHAR(255)       NOT NULL,
    token       VARCHAR(255) NOT NULL,
    expiry_date TIMESTAMP    NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);
