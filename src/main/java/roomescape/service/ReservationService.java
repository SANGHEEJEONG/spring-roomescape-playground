package roomescape.service;

import org.springframework.stereotype.Service;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.exception.MissingParameterException;
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
        if (reservationRequest.getName().isBlank()) {
            throw new MissingParameterException("name");
        }

        if (reservationRequest.getDate().isBlank()) {
            throw new MissingParameterException("date");
        }

        if (reservationRequest.getTime().isBlank()) {
            throw new MissingParameterException("time");
        }

        Long newId = id.incrementAndGet();
        ReservationResponse newReservation = ReservationResponse.toEntity(newId,reservationRequest);
        reservations.put(newId, newReservation);
        return newReservation;
    }

    public void deleteReservation(Long id){
        ReservationResponse reservation = reservations.get(id);

        if (reservation == null) {
            throw new NotFoundReservationException("Reservation not found");
        }

        reservations.remove(id);
    }
}
