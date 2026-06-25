package com.example.roomescape.theme;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository {
    Optional<Theme> findThemeById(Long id);
    List<Theme> findAllThemes();
}
