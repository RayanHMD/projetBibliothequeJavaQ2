package mvc.model;

import java.util.Date;

public class Reader {
    private Integer readerNumber;
    private String lastName;
    private String firstName;
    private Character gender;
    private String streetNumberAndName;
    private String numberPhone;
    private Date registrationDate;
    private Boolean hadPaidRegistration;
    private Date birthDate;
    private String email;
    private Location location;

    public Reader(Integer readerNumber, String lastName, String firstName, Character gender, String streetNumberAndName, String numberPhone, Date registrationDate, Boolean hadPaidRegistration, Date birthDate, String email, String nameLocation, Integer postalCode) {
        this.readerNumber = readerNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.streetNumberAndName = streetNumberAndName;
        this.numberPhone = numberPhone;
        this.registrationDate = registrationDate;
        this.hadPaidRegistration = hadPaidRegistration;
        this.birthDate = birthDate;
        this.email = email;
        this.location = LocationFactory.getLocation(nameLocation, postalCode);
    }

    public Reader(Integer readerNumber, String lastName, String firstName, String streetNumberAndName, String numberPhone, Date registrationDate, Boolean hadPaidRegistration, Date birthDate, String email, String nameLocation, Integer postalCode) {
        this(readerNumber, lastName, firstName, null, streetNumberAndName, numberPhone, registrationDate, hadPaidRegistration, birthDate, email, nameLocation, postalCode);
    }

    public Reader(Integer readerNumber, String lastName, String firstName, Character gender, String streetNumberAndName, Date registrationDate, Boolean hadPaidRegistration, Date birthDate, String email, String nameLocation, Integer postalCode) {
        this(readerNumber, lastName, firstName, gender, streetNumberAndName, null, registrationDate, hadPaidRegistration, birthDate, email, nameLocation, postalCode);
    }

    public Reader(Integer readerNumber, String lastName, String firstName, String streetNumberAndName, Date registrationDate, Boolean hadPaidRegistration, Date birthDate, String email, String nameLocation, Integer postalCode) {
        this(readerNumber, lastName, firstName, null, streetNumberAndName, null, registrationDate, hadPaidRegistration, birthDate, email, nameLocation, postalCode);
    }

    public Integer getReaderNumber() {
        return readerNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Character getGender() {
        return gender;
    }

    public String getStreetNumberAndName() {
        return streetNumberAndName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Boolean getHadPaidRegistration() {
        return hadPaidRegistration;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + readerNumber + ")";
    }
}
