package roomescape.dto;


import lombok.Getter;
import lombok.NonNull;

@Getter
public class Reservation {

    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String date;
    @NonNull
    private String time;

    public Reservation(Long id, String name, String date, String time){
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public static Reservation toEntity(Long id,Reservation reservation){
        return new Reservation(id,reservation.name,reservation.date,reservation.time);
    }
}
