package roomescape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.dao.ReservationDAO;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.entity.Reservation;
import roomescape.exception.NotFoundReservationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationDAO reservationDAO;

    private Map<Long, ReservationResponse> reservations = new HashMap<>();
    private AtomicLong id = new AtomicLong(0);

    public List<ReservationResponse> findAllReservations() {
        List<Reservation> reservations = reservationDAO.findAllReservations();

        // Reservation -> ReservationResponse 로 변환하여 반환
        List<ReservationResponse> reservationResponses = reservations.stream()
                .map(ReservationResponse::fromEntity)
                .toList();

        return reservationResponses;
    }

    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        Long newId = id.incrementAndGet();
        ReservationResponse newReservation = ReservationResponse.fromRequest(newId, reservationRequest);

        reservations.put(newId, newReservation);

        return newReservation;
    }

    public void deleteReservation(Long id) {
        ReservationResponse reservation = reservations.get(id);

        if (reservation == null) {
            throw new NotFoundReservationException();
        }

        reservations.remove(id);
    }
}
