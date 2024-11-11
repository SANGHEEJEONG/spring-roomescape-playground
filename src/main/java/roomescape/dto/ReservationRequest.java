package roomescape.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReservationRequest {

    @NotBlank(message = "name 값이 누락되었습니다.")
    private String name;
    @NotBlank(message = "date 값이 누락되었습니다.")
    private String date;
    @NotBlank(message = "time 값이 누락되었습니다.")
    private String time;
}
