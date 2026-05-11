package mvc.model;

import java.util.Date;

public class User {
    private int readerNumber;
    private String lastName;
    private String firstName;
    private String streetNumberAndName;
    private String numberPhone;
    private Date registrationDate;
    private boolean hadPaidRegistration;
    private Date birthDate;
    private String email;
    private Location nameLocation;
    private Location postalCode;

    public User(int readerNumber, String lastName, String firstName, String streetNumberAndName, String numberPhone, Date registrationDate, boolean hadPaidRegistration, Date birthDate, String email, Location nameLocation, Location postalCode) {
        this.readerNumber = readerNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.streetNumberAndName = streetNumberAndName;
        this.numberPhone = numberPhone;
        this.registrationDate = registrationDate;
        this.hadPaidRegistration = hadPaidRegistration;
        this.birthDate = birthDate;
        this.email = email;
        this.nameLocation = nameLocation;
        this.postalCode = postalCode;
    }
}
