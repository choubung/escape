package com.example.roomescape.reservationtime;

import java.util.List;
import java.util.Optional;

public interface ReservationTimeRepository {
    Optional<ReservationTime> readTimesById(Long id);
    List<ReservationTime> readAllTimes();
}
