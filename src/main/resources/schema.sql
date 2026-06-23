DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS reservation_times;
DROP TABLE IF EXISTS themes;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL
);

CREATE TABLE themes (
    theme_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(500),
    image_url VARCHAR(100) NOT NULL
);

CREATE TABLE reservation_times (
    time_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    start_at VARCHAR(20) NOT NULL
);

CREATE TABLE reservations (
    reservation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reservation_date DATE NOT NULL,
    user_id BIGINT NOT NULL,
    theme_id BIGINT NOT NULL,
    time_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (theme_id) REFERENCES themes(theme_id),
    FOREIGN KEY (time_id) REFERENCES reservation_times(time_id)
);