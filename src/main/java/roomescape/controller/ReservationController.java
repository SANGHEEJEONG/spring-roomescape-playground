package roomescape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.dto.Reservation;
import roomescape.exception.MissingParameterException;
import roomescape.exception.NotFoundReservationException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class ReservationController {

    private List<Reservation> reservations = new ArrayList<>();
    private AtomicLong id = new AtomicLong(1);

    @GetMapping("/reservation")
    public String accessReservationPage(){
        return "reservation";
    }

    @ResponseBody
    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations(){
        return ResponseEntity.ok().body(reservations);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> reserve(@RequestBody Reservation reservation){
        if (reservation.getName().isBlank()) {
            throw new MissingParameterException("name");
        }

        if (reservation.getDate().isBlank()) {
            throw new MissingParameterException("date");
        }

        if (reservation.getTime().isBlank()) {
            throw new MissingParameterException("time");
        }


        Reservation newReservation = Reservation.toEntity(id.incrementAndGet(),reservation);
        reservations.add(newReservation);

        return ResponseEntity.created(URI.create("/reservations/" + newReservation.getId())).body(newReservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id){
        Reservation reservation = reservations.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NotFoundReservationException("Reservation not found"));

        reservations.remove(reservation);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MissingParameterException.class)
    public ResponseEntity<String> handleMissingParameterException(MissingParameterException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NotFoundReservationException.class)
    public ResponseEntity handleException(NotFoundReservationException e) {
        return ResponseEntity.badRequest().build();
    }
}
