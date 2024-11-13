package roomescape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.dao.ReservationDAO;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.entity.Reservation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationDAO reservationDAO;

    public List<ReservationResponse> findAllReservations() {
        List<Reservation> reservations = reservationDAO.findAllReservations();

        List<ReservationResponse> reservationResponses = reservations.stream()
                .map(Reservation::toResponse)
                .toList();

        return reservationResponses;
    }

    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        Reservation reservation = reservationRequest.toEntity();

        reservation = reservationDAO.createReservation(reservation);

        ReservationResponse reservationResponse = reservation.toResponse();

        return reservationResponse;
    }

    public void deleteReservation(Long id) {
        reservationDAO.deleteReservation(id);
    }
}
