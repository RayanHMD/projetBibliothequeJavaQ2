package mvc.model;

import java.util.Date;

public class Reservation {
    private Integer id;
    private Date startDateOfBlockage;
    private Integer blockingDuration;

    public Reservation(int id, Date startDateOfBlockage, int blockingDuration) {
        this.id = id;
        this.startDateOfBlockage = startDateOfBlockage;
        this.blockingDuration = blockingDuration;
    }
}
