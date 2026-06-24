package com.example.roomescape.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserJdbcRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> readUserById(Long id) {
        String sql = """
                SELECT user_id, user_name
                FROM users
                WHERE user_id = ?
                """;

        return jdbcTemplate.query(sql, rowUserMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public List<User> readAllUsers() {
        String sql = """
                SELECT user_id, user_name
                FROM users
                """;

        return jdbcTemplate.query(sql, rowUserMapper());
    }

    private RowMapper<User> rowUserMapper() {
        return (rs, rowNum) -> {
            return User.builder()
                    .id(rs.getLong("user_id"))
                    .name(rs.getString("user_name"))
                    .build();
        };
    }
}
