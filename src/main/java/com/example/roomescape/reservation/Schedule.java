package com.example.roomescape.reservation;

import com.example.roomescape.reservationtime.ReservationTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Builder
public class Schedule {
    private final LocalDate date;
    private final ReservationTime time;
}
