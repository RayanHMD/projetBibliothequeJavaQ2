package mvc.model;

import java.util.Date;

public class Reservation {
    private int id;
    private Date startDateOfBlockage;
    private int blockingDuration;

    public Reservation(int id, Date startDateOfBlockage, int blockingDuration) {
        this.id = id;
        this.startDateOfBlockage = startDateOfBlockage;
        this.blockingDuration = blockingDuration;
    }
}
