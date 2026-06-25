package com.example.roomescape.reservation;

import com.example.roomescape.user.User;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    public Optional<Reservation> readReservationById(Long id);

    public List<Reservation> readAllReservations();

    public List<Reservation> readReservationsByUser(User user);

    public Optional<Reservation> readReservationsBySlot(Slot slot);
}
