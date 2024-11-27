package roomescape.repository;

import org.springframework.stereotype.Repository;
import roomescape.entity.ReservationTime;

import java.util.List;

@Repository
public interface ReservationTimeRepository {
    ReservationTime createReservationTime(ReservationTime reservationTime);
    List<ReservationTime> findAllReservationTimes();
    ReservationTime findReservationTimeById(Long id);
    void deleteReservationTime(Long id);
}
