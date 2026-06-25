package com.example.roomescape.reservation.dto.response;

import com.example.roomescape.reservation.Reservation;

import java.util.ArrayList;
import java.util.List;

public record ReservationResponses(
        List<ReservationResponse> responses
) {

    public static ReservationResponses from(List<Reservation> reservations) {
        List<ReservationResponse> responses = new ArrayList<>();

        for (Reservation reservation : reservations) {
            responses.add(ReservationResponse.from(reservation));
        }

        return new ReservationResponses(responses);
    }
}
