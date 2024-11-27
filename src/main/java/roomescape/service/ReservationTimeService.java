package roomescape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.dto.ReservationTimeRequest;
import roomescape.dto.ReservationTimeResponse;
import roomescape.entity.ReservationTime;
import roomescape.repository.ReservationTimeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationTimeService {

    private final ReservationTimeRepository reservationTimeRepository;

    public ReservationTimeResponse createReservationTime(ReservationTimeRequest reservationTimeRequest) {
        ReservationTime reservationTime = reservationTimeRequest.toEntity();

        reservationTime = reservationTimeRepository.save(reservationTime);

        ReservationTimeResponse reservationTimeResponse = ReservationTimeResponse.fromReservationTime(reservationTime);

        return reservationTimeResponse;
    }

    public List<ReservationTimeResponse> findAllReservationTimes() {
        List<ReservationTime> reservationTimes = reservationTimeRepository.findAll();

        List<ReservationTimeResponse> reservationTimeResponses = reservationTimes.stream()
                .map(ReservationTimeResponse::fromReservationTime)
                .toList();

        return reservationTimeResponses;
    }

    public ReservationTime findReservationTimeById(Long id){
        return reservationTimeRepository.findById(id);
    }

    public void deleteReservationTime(Long id) {
        reservationTimeRepository.delete(id);
    }
}
