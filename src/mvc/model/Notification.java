package mvc.model;

public class Notification {
    private User user;
    private Copy copy;
    private Reservation reservation;

    public Notification(User user, Copy copy, Reservation reservation) {
        this.user = user;
        this.copy = copy;
        this.reservation = reservation;
    }
}
