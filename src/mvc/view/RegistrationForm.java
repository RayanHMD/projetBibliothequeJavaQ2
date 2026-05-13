package mvc.view;


import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends JPanel{
    private JPanel formPanel;
    private JTextField firstName, lastName, email, numberPhone,
            streetNumberAndName, gender, birthDate;
    private JLabel firstNameLabel, lastNameLabel, emailLabel, numberPhoneLabel
            , streetNumberLabel, genderLabel, birthDateLabel, locationLabel;
    private JComboBox nameLocation;
    private JCheckBox hadPaidRegistration;
    private JButton registerButton, cancelButton;
    private MenuWindow parent;

    public RegistrationForm(MenuWindow parent){
        this.parent = parent;
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Formulaire d'inscription", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(20f));
        add(title, BorderLayout.NORTH);

        // Form
        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0,2,5,5));



        // FirstName
        firstNameLabel = new JLabel("Prénom : ");
        firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(firstNameLabel);
        firstName = new JTextField();
        firstName.setToolTipText("Entrer le prénom du membre");
        formPanel.add(firstName);

        // LastName
        lastNameLabel = new JLabel("Nom de famille : ");
        lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(lastNameLabel);
        lastName = new JTextField();
        lastName.setToolTipText("Entrer le nom de famille du membre");
        formPanel.add(lastName);

        // gender
        genderLabel = new JLabel("Genre du membre (optionnel): ");
        genderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(genderLabel);
        gender = new JTextField();
        gender.setToolTipText("Entrer le genre du membre");
        formPanel.add(gender);

        // birth date
        birthDateLabel = new JLabel("Date d'anniversaire du membre: ");
        birthDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(birthDateLabel);
        birthDate = new JTextField();
        birthDate.setToolTipText("Entrer la date d'anniversaire du membre");
        formPanel.add(birthDate);

        // email

        emailLabel = new JLabel("Email du membre: ");
        emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(emailLabel);
        email = new JTextField();
        email.setToolTipText("Entrer l'email du membre");
        formPanel.add(email);

        // numberPhone
        numberPhoneLabel = new JLabel("N° de téléphone (optionnel): ");
        numberPhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(numberPhoneLabel);
        numberPhone = new JTextField();
        numberPhone.setToolTipText("Entrer le n° de téléphone du membre");
        formPanel.add(numberPhone);



        // streetNumberAndName
        streetNumberLabel = new JLabel("Rue et numéro du membre: ");
        streetNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(streetNumberLabel);
        streetNumberAndName = new JTextField();
        streetNumberAndName.setToolTipText("Entrer la rue et le numéro du membre");
        formPanel.add(streetNumberAndName);


        // location
        locationLabel = new JLabel("Localité: ");
        locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(locationLabel);
        nameLocation = new JComboBox();
        nameLocation.setToolTipText("Choissisez votre continent d'origine");
        nameLocation.setEnabled(true);
        formPanel.add(nameLocation);



        add(formPanel, BorderLayout.CENTER);



    }

}
