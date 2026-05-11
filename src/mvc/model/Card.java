package mvc.model;

import java.util.Date;

public class Card {
    private int cardNumber;
    private Date expirationDate;
    private boolean isActive;
    private User user;

    public Card(int cardNumber, Date expirationDate, boolean isActive, User user) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.isActive = isActive;
        this.user = user;
    }
}
