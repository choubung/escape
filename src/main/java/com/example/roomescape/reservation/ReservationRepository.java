package com.example.roomescape.reservation;

import com.example.roomescape.user.User;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    public Optional<ReservationEntity> readReservationById(Long id);

    public List<ReservationEntity> readAllReservations();

    public List<ReservationEntity> readReservationsByUser(User user);

    public Optional<ReservationEntity> readReservationsBySlot(Slot slot);
}
