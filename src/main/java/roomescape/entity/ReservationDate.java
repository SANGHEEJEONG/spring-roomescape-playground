package roomescape.entity;

import lombok.Getter;
import roomescape.exception.InvalidReservationDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class ReservationDate {

    private final String date;

    public ReservationDate(String date) {
        validateDate(date);
        this.date = date;
    }

    private void validateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate inputDate = LocalDate.parse(date, formatter);

        LocalDate today = LocalDate.now();
        if (inputDate.isBefore(today)) {
            throw new InvalidReservationDateException();
        }
    }

    @Override
    public String toString() {
        return date;
    }
}
