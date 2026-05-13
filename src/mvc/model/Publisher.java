package mvc.model;

public class Publisher {
    private Integer idPublisher;
    private String name;
    private Location location;

    public Publisher(Integer idPublisher, String name, Location location) {
        this.idPublisher = idPublisher;
        this.name = name;
        this.location = location;
    }
}
