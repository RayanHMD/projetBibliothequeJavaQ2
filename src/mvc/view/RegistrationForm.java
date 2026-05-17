package mvc.view;


import mvc.controller.LocationController;
import mvc.controller.ReaderController;
import mvc.exception.DataAccessException;
import mvc.model.Location;
import mvc.model.Reader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class RegistrationForm extends JPanel{
    private JPanel formPanel, buttonPanel;
    private JTextField firstName, lastName, email, numberPhone, streetNumberAndName;
    private JSpinner birthDate;
    private JLabel firstNameLabel, lastNameLabel, emailLabel, numberPhoneLabel,
            streetNumberLabel, genderLabel, birthDateLabel, locationLabel, hadPaidRegistrationLabel;
    private JComboBox<Object>nameLocation;
    private JComboBox<String>gender;
    private JCheckBox hadPaidRegistration;
    private JButton inscriptionButton, cancelButton, resetButton;

    private MenuWindow parent;
    private LocationController locationController;
    private ReaderController readerController;
    private Reader readerToUpdate;

    public RegistrationForm(MenuWindow parent){
        this.parent = parent;
        this.locationController = new LocationController();
        this.readerController = new ReaderController();

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
        gender = new JComboBox();
        gender.setToolTipText("Entrer le genre du membre");
        gender.addItem("Ne pas préciser");
        gender.addItem("m");
        gender.addItem("f");
        gender.addItem("x");
        formPanel.add(gender);

        // birth date
        birthDateLabel = new JLabel("Date de naissance du membre: ");
        birthDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(birthDateLabel);

        SpinnerDateModel model = new SpinnerDateModel();
        birthDate = new JSpinner(model);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(birthDate, "dd/MM/yyyy");
        birthDate.setEditor(dateEditor);
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
        nameLocation.addItem("Choisir une localité");

        try{
            ArrayList<Location> locations = locationController.getAllLocations();
            for(Location loc : locations){
                nameLocation.addItem(loc);
            }

        }catch(DataAccessException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur Localité", JOptionPane.ERROR_MESSAGE);
        }

        formPanel.add(nameLocation);

        // had paid registration
        hadPaidRegistrationLabel = new JLabel("A payé l'inscription ?");
        hadPaidRegistrationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(hadPaidRegistrationLabel);
        hadPaidRegistration = new JCheckBox();
        hadPaidRegistration.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(hadPaidRegistration);

        add(formPanel, BorderLayout.CENTER);

        // button Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // button cancel
        cancelButton = new JButton("Annuler l'inscription");
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(e -> {
            parent.setAccueil();
        });

        // button inscription
        inscriptionButton = new JButton("Inscription");
        buttonPanel.add(inscriptionButton);
        inscriptionButton.addActionListener(e -> {

            if(checkForm() != null){
                JOptionPane.showMessageDialog(this, checkForm(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this, "Inscription réussie !");
            }

        });

        // reset button
        resetButton = new JButton("Réinitialiser");
        buttonPanel.add(resetButton);
        resetButton.addActionListener(e -> {
            firstName.setText("");
            lastName.setText("");
            email.setText("");
            numberPhone.setText("");
            streetNumberAndName.setText("");
            gender.setSelectedIndex(0);
            birthDate.setValue(new Date());
            nameLocation.setSelectedIndex(0);
            hadPaidRegistration.setSelected(false);
        });
        add(buttonPanel, BorderLayout.SOUTH);



    }

    private String checkForm() {

        if (firstName.getText().trim().isEmpty()) {
            return "Veuillez entrer un prénom";
        }

        if (lastName.getText().trim().isEmpty()) {
            return "Veuillez entrer un nom de famille";
        }

        Date selectedDate = (Date) birthDate.getValue();
        if(selectedDate.after(new Date())){
            return "Veuillez entrer une date corret, elle ne peut pas être dans le futur";
        }

        if (email.getText().trim().isEmpty()) {
            return "Veuillez entrer un email";
        }

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!email.getText().trim().matches(regex)) {
            return "Email invalide";
        }

        if (streetNumberAndName.getText().trim().isEmpty()) {
            return "Entrer une rue et un numéro";
        }

        if (nameLocation.getSelectedIndex() == 0) {
            return "Veuillez choisir une localité";
        }

        if (!hadPaidRegistration.isSelected()) {
            return "Le paiement de l'inscription est obligatoire";
        }

        return null;
    }

}
