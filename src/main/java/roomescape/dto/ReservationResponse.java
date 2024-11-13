package roomescape.dto;


import lombok.Getter;

@Getter
public class ReservationResponse {

    private Long id;
    private String name;
    private String date;
    private String time;

    public ReservationResponse(Long id, String name, String date, String time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

}
