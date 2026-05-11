package mvc.model;

public class Publisher {
    private int idPublisher;
    private String name;
    private Location location;

    public Publisher(int idPublisher, String name, Location location) {
        this.idPublisher = idPublisher;
        this.name = name;
        this.location = location;
    }
}
