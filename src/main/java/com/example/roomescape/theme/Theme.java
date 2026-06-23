package com.example.roomescape.theme;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Theme {
    private final Long id;
    private final String title;
    private final String description;
    private final String url;

    private Theme(Long id, String title, String description, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public static Theme create(String title, String description, String url) {
        return new Theme(null, title, description, url);
    }

    public static Theme from(Long id, String title, String description, String url) {
        return new Theme(id, title, description, url);
    }
}
