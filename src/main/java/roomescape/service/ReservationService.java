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
        Reservation reservation = convertToReservation(reservationRequest);

        reservation = reservationRepository.save(reservation);

        return ReservationResponse.fromReservation(reservation);
    }

    public List<ReservationResponse> findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        List<ReservationResponse> reservationResponses = reservations.stream()
                .map(ReservationResponse::fromReservation)
                .toList();

        return reservationResponses;
    }

    public void deleteReservation(Long id) {
        reservationRepository.delete(id);
    }

    private ReservationTime findReservationTime(ReservationRequest reservationRequest) {
        return reservationTimeService.findReservationTimeById(reservationRequest.timeId());
    }

    private Reservation convertToReservation(ReservationRequest reservationRequest) {
        ReservationTime reservationTime = findReservationTime(reservationRequest);
        return reservationRequest.toEntity(reservationTime);
    }
}
