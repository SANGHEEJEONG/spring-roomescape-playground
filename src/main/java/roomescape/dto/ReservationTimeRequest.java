package roomescape.dto;

import jakarta.validation.constraints.NotBlank;
import roomescape.entity.ReservationTime;

public record ReservationTimeRequest(
        @NotBlank(message = "reservationTime 값이 누락되었습니다.") String time
) {
    public ReservationTime toEntity() {
        return new ReservationTime(null, this.time);
    }
}
