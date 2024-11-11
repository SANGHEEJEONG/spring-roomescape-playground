package roomescape.service;

import org.springframework.stereotype.Service;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.exception.NotFoundReservationException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReservationService {

    private Map<Long, ReservationResponse> reservations = new HashMap<>();
    private AtomicLong id = new AtomicLong(0);

    public Map<Long, ReservationResponse> getReservations(){
        return reservations;
    }

    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        Long newId = id.incrementAndGet();
        ReservationResponse newReservation = ReservationResponse.toEntity(newId,reservationRequest);

        reservations.put(newId, newReservation);

        return newReservation;
    }

    public void deleteReservation(Long id){
        ReservationResponse reservation = reservations.get(id);

        if (reservation == null) {
            throw new NotFoundReservationException();
        }

        reservations.remove(id);
    }
}
