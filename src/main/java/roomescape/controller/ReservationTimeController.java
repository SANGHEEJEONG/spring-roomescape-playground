package roomescape.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.dto.ReservationTimeRequest;
import roomescape.dto.ReservationTimeResponse;
import roomescape.service.ReservationTimeService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationTimeController {

    private final ReservationTimeService reservationTimeService;

    @PostMapping("/times")
    public ResponseEntity<ReservationTimeResponse> createReservationTime(@RequestBody @Valid ReservationTimeRequest reservationTimeRequest) {
        ReservationTimeResponse newReservationTime = reservationTimeService.createReservationTime(reservationTimeRequest);

        return ResponseEntity.created(URI.create("/times/" + newReservationTime.id())).body(newReservationTime);
    }

    @GetMapping("/times")
    public ResponseEntity<List<ReservationTimeResponse>> findAllReservationTimes() {
        return ResponseEntity.ok().body(reservationTimeService.findAllReservationTimes());
    }

    @DeleteMapping("/times/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationTimeService.deleteReservationTime(id);

        return ResponseEntity.noContent().build();
    }
}
