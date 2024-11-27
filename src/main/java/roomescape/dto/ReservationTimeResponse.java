package roomescape.dto;

import roomescape.entity.ReservationTime;

public record ReservationTimeResponse(
        Long id,
        String reservationTime
) {
    public static ReservationTimeResponse fromReservationTime(ReservationTime reservationTime) {
        return new ReservationTimeResponse(
                reservationTime.getId(),
                reservationTime.getReservationTime()
        );
    }
}
