package com.example.roomescape.theme;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(ThemeJdbcRepository.class)
@Sql(scripts = {"/schema.sql", "/repository-test-data.sql"})
class ThemeJdbcRepositoryTest {

    @Autowired
    private ThemeRepository themeRepository;

    @Nested
    @DisplayName("테마 조회 테스트")
    class readTests {

        @DisplayName("테마 아이디로 특정 테마를 찾는다.")
        @Test
        void findReservaitonByIdTest() {
            // given
            Long id = 1L;

            // when
            Theme theme = themeRepository.findThemeById(1L).get();

            // then
            assertThat(theme.getTitle()).isEqualTo("테마 제목");
            assertThat(theme.getDescription()).isEqualTo("테마 내용입니다.");
            assertThat(theme.getUrl()).isEqualTo("테마url");
        }

        @DisplayName("존재하는 모든 테마를 찾는다.")
        @Test
        void getAllThemesTest() {
            // when
            List<Theme> times = themeRepository.findAllThemes();

            // then
            assertThat(times).hasSize(2);
        }
    }
}
