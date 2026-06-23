package com.example.roomescape.reservationtime;

import com.example.roomescape.reservation.ReservationEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationTimeJdbcRepository implements ReservationTimeRepository{
    private final JdbcTemplate jdbcTemplate;

    public ReservationTimeJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<ReservationTime> readTimesById(Long id) {
        String sql = """
                SELECT time_id, start_at
                FROM reservation_times
                WHERE time_id = ?
                """;

        return jdbcTemplate.query(sql, reservationTimeRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public List<ReservationTime> readAllTimes() {
        String sql = """
                SELECT time_id, start_at
                FROM reservation_times
                """;

        return jdbcTemplate.query(sql, reservationTimeRowMapper());
    }

    private RowMapper<ReservationTime> reservationTimeRowMapper() {
        return (rs, rowNum) -> {
            return ReservationTime.builder()
                    .id(rs.getLong("time_id"))
                    .startAt(LocalTime.parse(rs.getString("start_at")))
                    .build();
        };
    }
}
