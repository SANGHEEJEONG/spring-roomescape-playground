package roomescape.entity;


public class Reservation {

    private Long id;
    private String name;
    private String date;
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

    public Long getId() {
        return id;
    }
}
