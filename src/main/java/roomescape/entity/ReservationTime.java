package roomescape.entity;


import lombok.Getter;

@Getter
public class ReservationTime {
    private Long id;
    private String time;

    public ReservationTime(Long id, String time) {
        this.id = id;
        this.time = time;
    }
}
