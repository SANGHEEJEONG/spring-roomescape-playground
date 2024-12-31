package roomescape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.dto.ReservationTimeRequest;
import roomescape.dto.ReservationTimeResponse;
import roomescape.entity.ReservationTime;
import roomescape.exception.TimeDuplicateException;
import roomescape.repository.ReservationTimeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationTimeService {

    private final ReservationTimeRepository reservationTimeRepository;

    public ReservationTimeResponse createReservationTime(ReservationTimeRequest reservationTimeRequest) {
        ReservationTime reservationTime = reservationTimeRequest.toEntity();

        if (reservationTimeRepository.existsByTime(reservationTime.getTime())) {
            throw new TimeDuplicateException();
        }

        reservationTime = reservationTimeRepository.save(reservationTime);
        return ReservationTimeResponse.fromReservationTime(reservationTime);
    }

    public List<ReservationTimeResponse> findAllReservationTimes() {
        List<ReservationTime> reservationTimes = reservationTimeRepository.findAll();

        return reservationTimes.stream()
                .map(ReservationTimeResponse::fromReservationTime)
                .toList();
    }

    public void deleteReservationTime(Long id) {
        reservationTimeRepository.delete(id);
    }
}
