package roomescape.dto;

import roomescape.entity.ReservationTime;

public record ReservationTimeResponse(
        Long id,
        String time
) {
    public static ReservationTimeResponse fromReservationTime(ReservationTime reservationTime) {
        return new ReservationTimeResponse(
                reservationTime.getId(),
                reservationTime.getTime().toString()
        );
    }
}
