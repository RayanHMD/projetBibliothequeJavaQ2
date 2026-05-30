package mvc.model;

public class Publisher {
    private Integer idPublisher;
    private String name;
    private String streetNumberAndName;
    private Location location;

    public Publisher(Integer idPublisher, String name, String streetNumberAndName, Location location) {
        this.idPublisher = idPublisher;
        this.name = name;
        this.streetNumberAndName = streetNumberAndName;
        this.location = location;
    }
}
