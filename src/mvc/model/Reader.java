package mvc.model;

import java.util.Date;

public class Reader {
    private Integer readerNumber;
    private String lastName;
    private String firstName;
    private String streetNumberAndName;
    private String numberPhone;
    private Date registrationDate;
    private Boolean hadPaidRegistration;
    private Date birthDate;
    private String email;
    private Location location;

    public Reader(Integer readerNumber, String lastName, String firstName, String streetNumberAndName, String numberPhone, Date registrationDate, Boolean hadPaidRegistration, Date birthDate, String email, String nameLocation, Integer postalCode) {
        this.readerNumber = readerNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.streetNumberAndName = streetNumberAndName;
        this.numberPhone = numberPhone;
        this.registrationDate = registrationDate;
        this.hadPaidRegistration = hadPaidRegistration;
        this.birthDate = birthDate;
        this.email = email;
        this.location = LocationFactory.getLocation(nameLocation, postalCode);
    }
}
