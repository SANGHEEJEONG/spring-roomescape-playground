package roomescape.dto;


import jakarta.validation.constraints.NotBlank;
import roomescape.entity.Reservation;
import roomescape.entity.ReservationTime;

public record ReservationRequest(
        @NotBlank(message = "name 값이 누락되었습니다.") String name,
        @NotBlank(message = "date 값이 누락되었습니다.") String date,
        Long timeId
) {
    public Reservation toEntity(ReservationTime time) {
        return new Reservation(null, this.name, this.date, time);
    }
}
