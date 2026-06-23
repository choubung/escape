package com.example.roomescape.reservation;

import com.example.roomescape.reservationtime.ReservationTime;
import com.example.roomescape.theme.Theme;
import com.example.roomescape.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql(scripts = {"/schema.sql", "/repository-test-data.sql"})
class ReservationJdbcRepositoryTest {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationJdbcRepositoryTest(JdbcTemplate jdbcTemplate) {
        this.reservationRepository = new ReservationJdbcRepository(jdbcTemplate.getDataSource());
    }

    @Nested
    @DisplayName("예약 조회 테스트")
    class readTests {

        @DisplayName("예약 아이디로 특정 예약을 찾는다.")
        @Test
        void findReservaitonByIdTest() {
            // given
            Long id = 1L;

            // when
            ReservationEntity entity = reservationRepository.readReservationById(1L).get();

            // then
            assertThat(entity.getDate()).isEqualTo(LocalDate.parse("2026-10-03"));
            assertThat(entity.getTimeId()).isEqualTo(1);
            assertThat(entity.getThemeId()).isEqualTo(1);
            assertThat(entity.getUserId()).isEqualTo(1);
        }

        @DisplayName("존재하는 모든 예약을 찾는다.")
        @Test
        void getAllReservationsTest() {
            // when
            List<ReservationEntity> reservationEntities = reservationRepository.readAllReservations();

            // then
            assertThat(reservationEntities).hasSize(3);
        }

        @DisplayName("특정 유저의 모든 예약을 찾는다.")
        @Test
        void getReservationsByUserTest() {
            // given
            User user = User.from(1L, "파도");

            // when
            List<ReservationEntity> entities = reservationRepository.readReservationsByUser(user);

            // then
            assertThat(entities).hasSize(2);
            assertThat(entities)
                    .extracting(ReservationEntity::getId)
                    .contains(1L, 2L);
        }

        @DisplayName("특정 슬롯(테마/날짜/시간)의 예약을 찾는다.")
        @Test
        void getReservationsBySlotTest() {
            // given
            Schedule schedule = new Schedule(
                    LocalDate.parse("2026-10-03"),
                    ReservationTime.from(2L, LocalTime.of(15, 00))
            );
            Theme theme = Theme.from(1L, "테마 제목", "테마 내용입니다.", "테마url");
            Slot slot = new Slot(schedule, theme);

            // when
            ReservationEntity entity = reservationRepository.readReservationsBySlot(slot).get();

            // then
            assertThat(entity.getId()).isEqualTo(3);
            assertThat(entity.getDate()).isEqualTo(LocalDate.parse("2026-10-03"));
            assertThat(entity.getTimeId()).isEqualTo(2);
            assertThat(entity.getThemeId()).isEqualTo(1);
            assertThat(entity.getUserId()).isEqualTo(2);
        }
    }
}
