package com.example.roomescape.reservation;

import com.example.roomescape.reservationtime.ReservationTime;
import com.example.roomescape.theme.Theme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class Slot {
    private final Schedule schedule;
    private final Theme theme;

    public LocalDate getDate() {
        return schedule.getDate();
    }

    public ReservationTime getTime() {
        return schedule.getTime();
    }
}
