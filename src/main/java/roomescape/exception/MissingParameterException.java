package roomescape.exception;

public class MissingParameterException extends RoomescapeException {
    public MissingParameterException(String parameter) {
        super("파라미터" + parameter + "의 값이 누락되었습니다.");
    }
}
