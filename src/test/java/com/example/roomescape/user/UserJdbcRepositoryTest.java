package com.example.roomescape.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql(scripts = {"/schema.sql", "/repository-test-data.sql"})
class UserJdbcRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    public UserJdbcRepositoryTest(JdbcTemplate jdbcTemplate) {
        this.userRepository = new UserJdbcRepository(jdbcTemplate);
    }

    @Nested
    @DisplayName("유저 조회 테스트")
    class readTests {

        @DisplayName("유저 아이디로 특정 유저를 찾는다.")
        @Test
        void findReservationByIdTest() {
            // given
            Long id = 1L;

            // when
            User user = userRepository.readUserById(id).get();

            // then
            assertThat(user.getId()).isEqualTo(id);
            assertThat(user.getName()).isEqualTo("파도");
        }

        @DisplayName("존재하는 모든 유저을 찾는다.")
        @Test
        void getAllReservationsTest() {
            // when
            List<User> users = userRepository.readAllUsers();

            // then
            assertThat(users).hasSize(2);
            assertThat(users)
                    .extracting(User::getName)
                    .contains("파도", "코코");
        }
    }
}
