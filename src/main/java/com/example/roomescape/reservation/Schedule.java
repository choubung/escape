package com.example.roomescape.reservation;

import com.example.roomescape.reservationtime.ReservationTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class Schedule {
    private final LocalDate date;
    private final ReservationTime time;
}
