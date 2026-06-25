package com.example.roomescape.reservation;

import com.example.roomescape.reservationtime.ReservationTime;
import com.example.roomescape.theme.Theme;
import com.example.roomescape.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

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

    public LocalDate getDate() {
        return slot.getDate();
    }

    public ReservationTime getReservationTime() {
        return slot.getTime();
    }

    public Theme getTheme() {
        return slot.getTheme();
    }
}
