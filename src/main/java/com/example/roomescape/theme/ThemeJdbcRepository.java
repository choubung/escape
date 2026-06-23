package com.example.roomescape.theme;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class ThemeJdbcRepository implements ThemeRepository{
    private final JdbcTemplate jdbcTemplate;

    public ThemeJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Theme> readThemeById(Long id) {
        String sql = """
                SELECT theme_id, title, description, image_url
                FROM themes
                WHERE theme_id = ?     
                """;

        return jdbcTemplate.query(sql, themeRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public List<Theme> readAllThemes() {
        String sql = """
                SELECT theme_id, title, description, image_url
                FROM themes 
                """;

        return jdbcTemplate.query(sql, themeRowMapper());
    }

    private RowMapper<Theme> themeRowMapper() {
        return (rs, rowNum) -> {
            return Theme.builder()
                    .id(rs.getLong("theme_id"))
                    .title(rs.getString("title"))
                    .description(rs.getString("description"))
                    .url(rs.getString("image_url"))
                    .build();
        };
    }
}
