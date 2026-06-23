package com.example.roomescape.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Builder
public class ReservationEntity {
    private final Long id;
    private final LocalDate date;
    private final Long timeId;
    private final Long userId;
    private final Long themeId;
}
