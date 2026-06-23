package com.example.roomescape.theme;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository {
    Optional<Theme> readThemeById(Long id);
    List<Theme> readAllThemes();
}
