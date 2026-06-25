package com.example.roomescape.reservation;

import com.example.roomescape.reservationtime.ReservationTime;
import com.example.roomescape.theme.Theme;
import com.example.roomescape.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationJdbcRepository implements ReservationRepository {
    private final JdbcTemplate jdbcTemplate;

    public ReservationJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Reservation> findReservationById(Long id) {
        String sql = """
                SELECT r.reservation_id AS reservation_id, r.reservation_date AS reservation_date, 
                       u.user_id AS user_id, u.user_name AS user_name, 
                       th.theme_id AS theme_id, th.title AS title, th.description AS description, th.image_url AS image_url,
                       t.time_id AS time_id, t.start_at AS start_at
                FROM reservations r
                INNER JOIN reservation_times t ON r.time_id = t.time_id
                INNER JOIN themes th ON r.theme_id = th.theme_id
                INNER JOIN users u ON r.user_id = u.user_id
                WHERE reservation_id = ?
                """;
        return jdbcTemplate.query(sql, reservationRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public List<Reservation> findAllReservations() {
        String sql = """
                SELECT r.reservation_id AS reservation_id, r.reservation_date AS reservation_date, 
                       u.user_id AS user_id, u.user_name AS user_name, 
                       th.theme_id AS theme_id, th.title AS title, th.description AS description, th.image_url AS image_url,
                       t.time_id AS time_id, t.start_at AS start_at
                FROM reservations r
                INNER JOIN reservation_times t ON r.time_id = t.time_id
                INNER JOIN themes th ON r.theme_id = th.theme_id
                INNER JOIN users u ON r.user_id = u.user_id
                """;
        List<Reservation> reservations = jdbcTemplate.query(sql, reservationRowMapper());
        return reservations;
    }

    @Override
    public List<Reservation> findReservationsByUser(User user) {
        String sql = """
                SELECT r.reservation_id AS reservation_id, r.reservation_date AS reservation_date, 
                       u.user_id AS user_id, u.user_name AS user_name, 
                       th.theme_id AS theme_id, th.title AS title, th.description AS description, th.image_url AS image_url,
                       t.time_id AS time_id, t.start_at AS start_at
                FROM reservations r
                INNER JOIN reservation_times t ON r.time_id = t.time_id
                INNER JOIN themes th ON r.theme_id = th.theme_id
                INNER JOIN users u ON r.user_id = u.user_id
                WHERE u.user_id = ?
                """;
        return jdbcTemplate.query(sql, reservationRowMapper(), user.getId());
    }

    @Override
    public Optional<Reservation> findReservationsBySlot(Slot slot) {
        String sql = """
                SELECT r.reservation_id AS reservation_id, r.reservation_date AS reservation_date, 
                       u.user_id AS user_id, u.user_name AS user_name, 
                       th.theme_id AS theme_id, th.title AS title, th.description AS description, th.image_url AS image_url,
                       t.time_id AS time_id, t.start_at AS start_at
                FROM reservations r
                INNER JOIN reservation_times t ON r.time_id = t.time_id
                INNER JOIN themes th ON r.theme_id = th.theme_id
                INNER JOIN users u ON r.user_id = u.user_id
                WHERE r.reservation_date = ? AND r.theme_id = ? AND r.time_id = ?
                """;
        return jdbcTemplate.query(sql, reservationRowMapper(), Date.from(slot.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()), slot.getTheme().getId(), slot.getTime().getId())
                .stream()
                .findFirst();
    }

    private RowMapper<Reservation> reservationRowMapper() {
        return (rs, rowNum) -> {
            ReservationTime time = ReservationTime.builder()
                    .id(rs.getLong("time_id"))
                    .startAt(LocalTime.parse(rs.getString("start_at")))
                    .build();

            Theme theme = Theme.builder()
                    .id(rs.getLong("theme_id"))
                    .title(rs.getString("title"))
                    .description(rs.getString("description"))
                    .url(rs.getString("image_url"))
                    .build();

            User user = User.builder()
                    .id(rs.getLong("user_id"))
                    .name(rs.getString("user_name"))
                    .build();

            return Reservation.builder()
                    .id(rs.getLong("reservation_id"))
                    .user(user)
                    .slot(Slot.builder()
                            .schedule(Schedule.builder()
                                    .date(rs.getDate("reservation_date").toLocalDate())
                                    .time(time)
                                    .build())
                            .theme(theme)
                            .build())
                    .build();
        };
    }
}
