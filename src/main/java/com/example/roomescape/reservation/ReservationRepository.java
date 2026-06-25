package com.example.roomescape.reservation;

import com.example.roomescape.user.User;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Optional<Reservation> findReservationById(Long id);

    List<Reservation> findAllReservations();

    List<Reservation> findReservationsByUser(User user);

    Optional<Reservation> findReservationsBySlot(Slot slot);
}
