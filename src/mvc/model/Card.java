package mvc.model;

import java.util.Date;

public class Card {
    private Integer cardNumber;
    private Date expirationDate;
    private Boolean isActive;
    private User user;

    public Card(Integer cardNumber, Date expirationDate, Boolean isActive, User user) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.isActive = isActive;
        this.user = user;
    }
}
