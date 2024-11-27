package roomescape.repository;

import org.springframework.stereotype.Repository;
import roomescape.entity.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository {
    Reservation save(Reservation reservation);
    List<Reservation> findAll();
    void delete(Long id);
}
