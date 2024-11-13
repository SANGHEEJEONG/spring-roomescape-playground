package roomescape.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import roomescape.entity.Reservation;

@Getter
public class ReservationRequest {

    @NotBlank(message = "name 값이 누락되었습니다.")
    private String name;
    @NotBlank(message = "date 값이 누락되었습니다.")
    private String date;
    @NotBlank(message = "time 값이 누락되었습니다.")
    private String time;

    public Reservation toEntity() {
        return new Reservation(null, this.name, this.date, this.time);
    }
}
