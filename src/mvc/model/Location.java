package mvc.model;

public final class Location {
    private final String name;
    private final Integer postalCode;

    Location(String name, Integer postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return name + " (" + postalCode + ")";
    }
}
