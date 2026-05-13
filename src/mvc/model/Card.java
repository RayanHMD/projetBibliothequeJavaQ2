package mvc.model;

import java.util.Date;

public class Card {
    private Integer cardNumber;
    private Date expirationDate;
    private Boolean isActive;
    private Reader reader;

    public Card(Integer cardNumber, Date expirationDate, Boolean isActive, Reader reader) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.isActive = isActive;
        this.reader = reader;
    }
}
