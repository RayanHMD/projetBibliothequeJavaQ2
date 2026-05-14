package mvc.model;

import java.util.Date;

public class Card {
    private Integer cardNumber;
    private Date expirationDate;
    private Reader reader;

    public Card(Integer cardNumber, Date expirationDate, Reader reader) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.reader = reader;
    }
}
