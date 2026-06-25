package com.example.roomescape.reservation.dto.response;

import com.example.roomescape.reservation.Reservation;
import com.example.roomescape.reservation.Slot;
import com.example.roomescape.user.User;
import lombok.Builder;

@Builder
public record ReservationResponse(
        Long id,
        User user,
        Slot slot
) {

    public static ReservationResponse from(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .user(reservation.getUser())
                .slot(reservation.getSlot())
                .build();
    }
}
