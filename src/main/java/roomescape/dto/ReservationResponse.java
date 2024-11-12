package roomescape.dto;


import lombok.Getter;
import roomescape.entity.Reservation;

@Getter
public class ReservationResponse {

    private Long id;
    private String name;
    private String date;
    private String time;

    public ReservationResponse(Long id, String name, String date, String time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public static ReservationResponse fromRequest(Long id, ReservationRequest reservationRequest) {
        return new ReservationResponse(id, reservationRequest.getName(), reservationRequest.getDate(), reservationRequest.getTime());
    }

    public static ReservationResponse fromEntity(Reservation reservation) {
        return new ReservationResponse(reservation.getId(),reservation.getName(),reservation.getDate().getDate(),reservation.getTime());
    }
}
