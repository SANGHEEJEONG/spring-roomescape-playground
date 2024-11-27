package roomescape.entity;


import lombok.Getter;

@Getter
public class ReservationTime {
    private Long id;
    private String reservationTime;

    public ReservationTime(Long id, String reservationTime) {
        this.id = id;
        this.reservationTime = reservationTime;
    }
}
