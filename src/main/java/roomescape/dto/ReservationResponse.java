package roomescape.dto;

import roomescape.entity.Reservation;
import roomescape.entity.ReservationTime;

public record ReservationResponse(
        Long id,
        String name,
        String date,
        ReservationTime time
) {
    public static ReservationResponse fromReservation(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getName(),
                reservation.getDate().toString(),
                reservation.getTime()
        );
    }
}
