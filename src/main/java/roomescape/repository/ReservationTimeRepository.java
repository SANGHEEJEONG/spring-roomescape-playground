package roomescape.repository;

import org.springframework.stereotype.Repository;
import roomescape.entity.ReservationTime;

@Repository
public interface ReservationTimeRepository {
    ReservationTime createReservationTime(ReservationTime reservationTime);
}
