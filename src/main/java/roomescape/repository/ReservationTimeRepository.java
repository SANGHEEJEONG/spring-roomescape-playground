package roomescape.repository;

import org.springframework.stereotype.Repository;
import roomescape.entity.ReservationTime;

import java.util.List;

@Repository
public interface ReservationTimeRepository {
    ReservationTime save(ReservationTime reservationTime);
    List<ReservationTime> findAll();
    ReservationTime findById(Long id);
    void delete(Long id);
}
