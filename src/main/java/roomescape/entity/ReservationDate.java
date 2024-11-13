package roomescape.entity;

import lombok.Getter;

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
            throw new IllegalArgumentException("지난 날짜는 예약이 불가합니다.");
        }
    }

    @Override
    public String toString() {
        return date;
    }
}
