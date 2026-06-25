package com.example.roomescape.reservation;

import com.example.roomescape.reservation.dto.request.SlotRequest;
import com.example.roomescape.reservation.dto.response.ReservationResponse;
import com.example.roomescape.reservation.dto.response.ReservationResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private ReservationService reservationService;

    @GetMapping("/{id}")
    public ReservationResponse getReservationById(
            @PathVariable Long id
    ) {
        ReservationResponse response = ReservationResponse.from(reservationService.findById(id));

        return response;
    }

    @GetMapping
    public ReservationResponses getAllReservations() {
        return ReservationResponses.from(reservationService.findAllReservations());
    }

    @GetMapping("/my-reservations")
    public ReservationResponses getReservationsByUser(
            @RequestParam Long userId
    ) {
        List<Reservation> reservations = reservationService.findReservationsByUser(userId);
        ReservationResponses responses = ReservationResponses.from(reservations);
        return responses;
    }

    @GetMapping("/theme-reservation")
    public ReservationResponse getReservationsBySlot(
            @RequestParam SlotRequest slotRequest
    ) {
        Reservation reservation = reservationService.findReservationBySlot(slotRequest);
        ReservationResponse response = ReservationResponse.from(reservation);

        return response;
    }
}
