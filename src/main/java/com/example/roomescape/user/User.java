package com.example.roomescape.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
public class User {
    private final Long id;
    private final String name;

    private User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static User create(String name) {
        return new User(null, name);
    }

    public static User from(Long id, String name) {
        return new User(id, name);
    }
}
