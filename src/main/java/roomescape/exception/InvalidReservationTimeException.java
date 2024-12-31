package roomescape.exception;


public class InvalidReservationTimeException extends RoomescapeException {
    public InvalidReservationTimeException() {
        super("시간 형식이 올바르지 않습니다.");
    }
}
