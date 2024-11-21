package roomescape.exception;


public class InvalidReservationDateException extends RoomescapeException {
    public InvalidReservationDateException() {
        super("지난 날짜는 예약이 불가합니다.");
    }
}
