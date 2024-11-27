package roomescape.exception;

public class NotFoundException extends RoomescapeException {
    public NotFoundException(String entityName) {
        super(entityName + "을 찾을 수 없습니다.");
    }
}
