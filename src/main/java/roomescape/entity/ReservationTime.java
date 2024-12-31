package roomescape.entity;


import lombok.Getter;
import roomescape.exception.InvalidReservationTimeException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
public class ReservationTime {
    private Long id;
    private LocalTime time;

    public ReservationTime(Long id, String time) {
        this.id = id;
        this.time = parseToLocalTime(time);
    }

    private LocalTime parseToLocalTime(String timeStr) throws InvalidReservationTimeException {
        try {
            return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new InvalidReservationTimeException();
        }
    }

    @Override
    public String toString() {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
