package com.example.roomescape.reservation.dto.request;

import java.time.LocalDate;

public record SlotRequest(
        LocalDate date,
        Long timeId,
        Long themeId
) {
}
