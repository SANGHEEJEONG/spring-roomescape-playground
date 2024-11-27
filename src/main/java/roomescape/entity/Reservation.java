package roomescape.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Reservation {

    private Long id;
    private String name;
    private ReservationDate date;
    private ReservationTime time;

    public Reservation(Long id, String name, String date, ReservationTime time) {
        this.id = id;
        this.name = name;
        this.date = new ReservationDate(date);
        this.time = time;
    }
}
