package com.example.roomescape.reservation;

import com.example.roomescape.reservation.dto.request.SlotRequest;
import com.example.roomescape.reservationtime.ReservationTime;
import com.example.roomescape.reservationtime.ReservationTimeRepository;
import com.example.roomescape.theme.Theme;
import com.example.roomescape.theme.ThemeRepository;
import com.example.roomescape.user.User;
import com.example.roomescape.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ReservationTimeRepository timeRepository;
    private final ThemeRepository themeRepository;


    public Reservation findById(Long id) {
        return reservationRepository.findReservationById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));
    }

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAllReservations();
    }

    public List<Reservation> findReservationsByUser(Long userId) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        return reservationRepository.findReservationsByUser(user);
    }

    public Reservation findReservationBySlot(SlotRequest request) {
        ReservationTime time = timeRepository.findTimesById(request.timeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시간입니다."));

        Theme theme = themeRepository.findThemeById(request.themeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 테마입니다."));

        Slot slot = Slot.builder()
                .schedule(Schedule.builder()
                        .date(request.date())
                        .time(time)
                        .build())
                .theme(theme)
                .build();

        return reservationRepository.findReservationsBySlot(slot)
                .orElseThrow(() -> new IllegalArgumentException("해당 슬롯의 예약이 존재하지 않습니다."));
    }
}
