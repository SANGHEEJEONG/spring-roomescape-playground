package roomescape.dto;

import jakarta.validation.constraints.NotBlank;
import roomescape.entity.Reservation;

public record ReservationRequest(
        @NotBlank(message = "name 값이 누락되었습니다.") String name,
        @NotBlank(message = "date 값이 누락되었습니다.") String date,
        @NotBlank(message = "time 값이 누락되었습니다.") String time
) {
    public Reservation toEntity() {
        return new Reservation(null, this.name, this.date, this.time);
    }
}
