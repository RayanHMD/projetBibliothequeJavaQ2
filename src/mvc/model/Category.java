package mvc.model;

public class Category {
    private String label;
    private Double rentalPrice;

    public Category(String label, Double rentalPrice) {
        this.label = label;
        this.rentalPrice = rentalPrice;
    }

    public String getLabel() {
        return label;
    }

    public Double getRentalPrice() {
        return rentalPrice;
    }
}
