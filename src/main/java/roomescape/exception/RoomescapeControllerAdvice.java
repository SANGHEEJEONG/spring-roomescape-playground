package roomescape.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class RoomescapeControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        log.error("파라미터 형식 에러 발생", e);

        // BindingResult -> 오류 목록 저장
        e.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = "입력값: [" + error.getRejectedValue() + "] - " + error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(NotFoundReservationException.class)
    public ResponseEntity<String> handleNotFoundReservationException(NotFoundReservationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(InvalidReservationDateException.class)
    public ResponseEntity<String> handleInvalidReservationDateException(InvalidReservationDateException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
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
