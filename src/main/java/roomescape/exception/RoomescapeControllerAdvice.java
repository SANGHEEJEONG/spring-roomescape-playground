package roomescape.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RoomescapeControllerAdvice {

    @ExceptionHandler(MissingParameterException.class)
    public ResponseEntity<String> handleMissingParameterException(MissingParameterException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NotFoundReservationException.class)
    public ResponseEntity<Void> handleNotFoundReservationException(NotFoundReservationException e) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("");
        return ResponseEntity.internalServerError().body("의도되지 않은 에러가 발생했습니다");
    }

    @ExceptionHandler(RoomescapeException.class)
    public ResponseEntity<String> handleExceptions(RoomescapeException e) {
        log.error("");
        return ResponseEntity.internalServerError().body("프로그램 내 에러가 발생했습니다.");
    }
}
