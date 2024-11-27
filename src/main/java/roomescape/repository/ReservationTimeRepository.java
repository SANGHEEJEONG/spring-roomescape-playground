package roomescape.repository;

import org.springframework.stereotype.Repository;
import roomescape.entity.ReservationTime;

import java.util.List;

@Repository
public interface ReservationTimeRepository {
    ReservationTime createReservationTime(ReservationTime reservationTime);
    List<ReservationTime> findAllReservationTimes();
    void deleteReservationTime(Long id);
}
