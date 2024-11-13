package roomescape.entity;

import lombok.Getter;
import roomescape.dto.ReservationResponse;

@Getter
public class Reservation {

    private final Long id;
    private final String name;
    private final ReservationDate date;
    private final String time;

    public Reservation(Long id, String name, String date, String time) {
        this.id = id;
        this.name = name;
        this.date = new ReservationDate(date);
        this.time = time;
    }

    public ReservationResponse toResponse() {
        return new ReservationResponse(this.id, this.name, this.date.toString(), this.time);
    }
}
