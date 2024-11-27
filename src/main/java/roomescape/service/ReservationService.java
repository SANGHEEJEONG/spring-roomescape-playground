package roomescape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.entity.Reservation;
import roomescape.entity.ReservationTime;
import roomescape.repository.ReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationTimeService reservationTimeService;

    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        ReservationTime reservationTime = findReservationTime(reservationRequest);
        Reservation reservation = reservationRequest.toEntity(reservationTime);

        reservation = reservationRepository.createReservation(reservation);

        return ReservationResponse.fromReservation(reservation);
    }

    public List<ReservationResponse> findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAllReservations();

        List<ReservationResponse> reservationResponses = reservations.stream()
                .map(ReservationResponse::fromReservation)
                .toList();

        return reservationResponses;
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteReservation(id);
    }

    private ReservationTime findReservationTime(ReservationRequest reservationRequest) {
        return reservationTimeService.findReservationTimeById(reservationRequest.timeId());
    }
}
