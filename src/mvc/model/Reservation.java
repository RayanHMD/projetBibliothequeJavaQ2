package mvc.model;

import java.util.Date;

public class Reservation {
    private Integer idReservation;
    private Date startDateOfBlockage;
    private Integer blockingDuration;

    public Reservation(int idReservation, Date startDateOfBlockage, int blockingDuration) {
        this.idReservation = idReservation;
        this.startDateOfBlockage = startDateOfBlockage;
        this.blockingDuration = blockingDuration;
    }
}
