package roomescape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.entity.Reservation;
import roomescape.repository.ReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<ReservationResponse> findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAllReservations();

        List<ReservationResponse> reservationResponses = reservations.stream()
                .map(Reservation::toResponse)
                .toList();

        return reservationResponses;
    }

    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        Reservation reservation = reservationRequest.toEntity();

        reservation = reservationRepository.createReservation(reservation);

        ReservationResponse reservationResponse = reservation.toResponse();

        return reservationResponse;
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteReservation(id);
    }
}
