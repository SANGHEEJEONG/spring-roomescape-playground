package roomescape.repository;

import org.springframework.stereotype.Repository;
import roomescape.entity.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository {
    Reservation createReservation(Reservation reservation);
    List<Reservation> findAllReservations();
    void deleteReservation(Long id);
}
