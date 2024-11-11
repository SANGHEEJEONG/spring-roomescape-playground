package roomescape.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.service.ReservationService;

import java.net.URI;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ReservationRestController {

private final ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity<Map<Long, ReservationResponse>> getReservations(){
        return ResponseEntity.ok().body(reservationService.getReservations());
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest reservationRequest){
        ReservationResponse newReservation = reservationService.createReservation(reservationRequest);

        return ResponseEntity.created(URI.create("/reservations/" + newReservation.getId())).body(newReservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id){
        reservationService.deleteReservation(id);

        return ResponseEntity.noContent().build();
    }


}


