package com.example.roomescape.reservationtime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql(scripts = {"/schema.sql", "/repository-test-data.sql"})
class ReservationTimeJdbcRepositoryTest {

    private final ReservationTimeRepository timeRepository;

    @Autowired
    public ReservationTimeJdbcRepositoryTest(JdbcTemplate jdbcTemplate) {
        this.timeRepository = new ReservationTimeJdbcRepository(jdbcTemplate.getDataSource());
    }

    @Nested
    @DisplayName("예약 시간 조회 테스트")
    class readTests {

        @DisplayName("예약 시간 아이디로 특정 예약 시간을 찾는다.")
        @Test
        void findReservationTimeByIdTest() {
            // given
            Long id = 1L;

            // when
            ReservationTime time = timeRepository.readTimesById(1L).get();

            // then
            assertThat(time.getStartAt()).isEqualTo(LocalTime.of(13, 00));
        }

        @DisplayName("존재하는 모든 예약 시간을 찾는다.")
        @Test
        void getAllReservationTimesTest() {
            // when
            List<ReservationTime> times = timeRepository.readAllTimes();

            // then
            assertThat(times).hasSize(2);
        }
    }
}
