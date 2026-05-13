package mvc.model;

public class Notification {
    private Reader user;
    private Copy copy;
    private Reservation reservation;

    public Notification(Reader user, Copy copy, Reservation reservation) {
        this.user = user;
        this.copy = copy;
        this.reservation = reservation;
    }
}
