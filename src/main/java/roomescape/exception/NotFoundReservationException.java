package roomescape.exception;

public class NotFoundReservationException extends RoomescapeException {
    public NotFoundReservationException(String entityName) {
        super(entityName + "을 찾을 수 없습니다.");
    }
}
