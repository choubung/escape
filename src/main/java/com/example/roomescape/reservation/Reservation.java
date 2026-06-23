package com.example.roomescape.reservation;

import com.example.roomescape.user.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Reservation {
    private final Long id;
    private final User user;
    private final Slot slot;

    private Reservation(Long id, User user, Slot slot) {
        this.id = id;
        this.user = user;
        this.slot = slot;
    }

    public static Reservation create(User user, Slot slot) {
        return new Reservation(null, user, slot);
    }

    public static Reservation from(Long id, User user, Slot slot) {
        return new Reservation(id, user, slot);
    }
}
