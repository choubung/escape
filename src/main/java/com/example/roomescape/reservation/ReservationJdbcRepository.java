package com.example.roomescape.reservation;

import com.example.roomescape.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationJdbcRepository implements ReservationRepository {
    private final JdbcTemplate jdbcTemplate;

    public ReservationJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<ReservationEntity> readReservationById(Long id) {
        String sql = """
                SELECT reservation_id, reservation_date, user_id, theme_id, time_id
                FROM reservations
                WHERE reservation_id = ?
                """;
        return jdbcTemplate.query(sql, reservationEntityRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public List<ReservationEntity> readAllReservations() {
        String sql = """
                SELECT reservation_id, reservation_date, user_id, theme_id, time_id
                FROM reservations
                """;
        List<ReservationEntity> entities = jdbcTemplate.query(sql, reservationEntityRowMapper());
        return entities;
    }

    @Override
    public List<ReservationEntity> readReservationsByUser(User user) {
        String sql = """
                SELECT reservation_id, reservation_date, user_id, theme_id, time_id
                FROM reservations
                WHERE user_id = ?
                """;
        return jdbcTemplate.query(sql, reservationEntityRowMapper(), user.getId());
    }

    @Override
    public Optional<ReservationEntity> readReservationsBySlot(Slot slot) {
        String sql = """
                SELECT reservation_id, reservation_date, user_id, theme_id, time_id
                FROM reservations
                WHERE reservation_date = ? AND theme_id = ? AND time_id = ?
                """;
        return jdbcTemplate.query(sql, reservationEntityRowMapper(), Date.from(slot.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()), slot.getTheme().getId(), slot.getTime().getId())
                .stream()
                .findFirst();
    }

    private RowMapper<ReservationEntity> reservationEntityRowMapper() {
        return (rs, rowNum) -> {
            return ReservationEntity.builder()
                    .id(rs.getLong("reservation_id"))
                    .date(rs.getDate("reservation_date").toLocalDate())
                    .timeId(rs.getLong("time_id"))
                    .themeId(rs.getLong("theme_id"))
                    .userId(rs.getLong("user_id"))
                    .build();
        };
    }
}
