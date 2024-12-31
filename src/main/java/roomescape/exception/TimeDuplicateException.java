package roomescape.exception;

public class TimeDuplicateException extends RoomescapeException {
    public TimeDuplicateException() {
        super("해당 시간은 이미 추가되었습니다.");
    }
}
