package mvc.view;


import mvc.controller.LocationController;
import mvc.controller.ReaderController;
import exception.DataAccessException;
import exception.BusinessException;
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
    private JLabel title, firstNameLabel, lastNameLabel, emailLabel, numberPhoneLabel,
            streetNumberLabel, genderLabel, birthDateLabel, locationLabel, hadPaidRegistrationLabel;
    private JComboBox<Object> nameLocation;
    private JComboBox<String> gender;
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
        title = new JLabel("Formulaire d'inscription", SwingConstants.CENTER);
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
        firstName.setToolTipText("Entrer le prénom du lecteur");
        formPanel.add(firstName);

        // LastName
        lastNameLabel = new JLabel("Nom de famille : ");
        lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(lastNameLabel);
        lastName = new JTextField();
        lastName.setToolTipText("Entrer le nom de famille du lecteur");
        formPanel.add(lastName);

        // Gender
        genderLabel = new JLabel("Genre du lecteur (optionnel): ");
        genderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(genderLabel);
        gender = new JComboBox<>();
        gender.setToolTipText("Entrer le genre du lecteur");
        gender.addItem("Ne pas préciser");
        gender.addItem("m");
        gender.addItem("f");
        gender.addItem("x");
        formPanel.add(gender);

        // birth date
        birthDateLabel = new JLabel("Date de naissance du lecteur: ");
        birthDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(birthDateLabel);

        SpinnerDateModel model = new SpinnerDateModel();
        birthDate = new JSpinner(model);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(birthDate, "dd/MM/yyyy");
        birthDate.setEditor(dateEditor);
        birthDate.setToolTipText("Entrer la date d'anniversaire du lecteur");
        formPanel.add(birthDate);

        // email
        emailLabel = new JLabel("Email du lecteur: ");
        emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(emailLabel);
        email = new JTextField();
        email.setToolTipText("Entrer l'email du lecteur");
        formPanel.add(email);

        // numberPhone
        numberPhoneLabel = new JLabel("N° de téléphone (optionnel): ");
        numberPhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(numberPhoneLabel);
        numberPhone = new JTextField();
        numberPhone.setToolTipText("Entrer le n° de téléphone du lecteur");
        formPanel.add(numberPhone);



        // streetNumberAndName
        streetNumberLabel = new JLabel("Rue et numéro du lecteur: ");
        streetNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(streetNumberLabel);
        streetNumberAndName = new JTextField();
        streetNumberAndName.setToolTipText("Entrer la rue et le numéro du lecteur");
        formPanel.add(streetNumberAndName);


        // location
        locationLabel = new JLabel("Localité: ");
        locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(locationLabel);
        nameLocation = new JComboBox<>();
        nameLocation.addItem("Choisir une localité");

        try{
            ArrayList<Location> locations = locationController.getAllLocations();
            for(Location loc : locations){
                nameLocation.addItem(loc);
            }

        } catch(DataAccessException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur Localité", JOptionPane.ERROR_MESSAGE);
        }

        formPanel.add(nameLocation);

        // had paid registration
        hadPaidRegistrationLabel = new JLabel("A payé(e) l'inscription ?");
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
            if(readerToUpdate == null){
                this.parent.setAccueil();
            }
            else {
                this.parent.showReaderList();
            }
        });

        // button inscription
        inscriptionButton = new JButton("Inscription");
        buttonPanel.add(inscriptionButton);
        inscriptionButton.addActionListener(e -> {
            String error = checkForm();

            if(error != null){
                JOptionPane.showMessageDialog(this, error, "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else{
                try {
                    Reader reader = createReaderFromForm();

                    if(readerToUpdate == null) {
                        readerController.addReader(reader);
                        JOptionPane.showMessageDialog(this, "Inscription réussie !");
                        this.parent.setAccueil();
                    }
                    else {
                        readerController.updateReader(reader);
                        JOptionPane.showMessageDialog(this, "Lecteur modifié !");
                        this.parent.showReaderList();
                    }

                } catch (DataAccessException | BusinessException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
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

    public RegistrationForm(MenuWindow parent, Reader readerToUpdate) {
        this(parent);
        this.readerToUpdate = readerToUpdate;
        fillForm(readerToUpdate);
        inscriptionButton.setText("Modifier");
        resetButton.setEnabled(false);
        cancelButton.setText("Annuler la modification");
        title.setText("Modification d'un lecteur");
    }


    private String checkForm() {

        if (firstName.getText().trim().isEmpty()) {
            return "Veuillez entrer un prénom";
        }
        if(firstName.getText().trim().length() > 100) {
            return "Le prénom ne peut pas dépasser 100 caractères.";
        }

        if (lastName.getText().trim().isEmpty()) {
            return "Veuillez entrer un nom de famille";
        }
        if(lastName.getText().trim().length() > 100) {
            return "Le prénom ne peut pas dépasser 100 caractères.";
        }

        Date selectedDate = (Date) birthDate.getValue();
        if(selectedDate.after(new Date())){
            return "Veuillez entrer une date correcte, elle ne peut pas être dans le futur";
        }

        if (email.getText().trim().isEmpty()) {
            return "Veuillez entrer un email";
        }

        if(email.getText().trim().length() > 255) {
            return "L'email ne peut pas dépasser 255 caractères.";
        }
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.getText().trim().matches(regex)) {
            return "Email invalide";
        }

        if(!numberPhone.getText().trim().isEmpty() && numberPhone.getText().trim().length() > 20){
            return "Le numéro de téléphone ne peut pas dépasser 20 caractères.";
        }

        if (streetNumberAndName.getText().trim().isEmpty()) {
            return "Entrer une rue et un numéro";
        }
        if(streetNumberAndName.getText().trim().length() > 150) {
            return "L'adresse ne peut pas dépasser 150 caractères.";
        }

        if (nameLocation.getSelectedIndex() == 0) {
            return "Veuillez choisir une localité";
        }

        return null;
    }

    private void fillForm(Reader reader) {
        firstName.setText(reader.getFirstName());
        lastName.setText(reader.getLastName());
        email.setText(reader.getEmail());
        streetNumberAndName.setText(reader.getStreetNumberAndName());
        birthDate.setValue(reader.getBirthDate());

        if(reader.getNumberPhone() != null) {
            numberPhone.setText(reader.getNumberPhone());
        }

        if(reader.getGender() == null) {
            gender.setSelectedIndex(0);
        }
        else {
            gender.setSelectedItem(reader.getGender().toString());
        }

        int index = 0;
        boolean locationFound = false;

        while(index < nameLocation.getItemCount() && !locationFound) {
            Object item = nameLocation.getItemAt(index);

            if(item instanceof Location) {
                Location location = (Location) item;

                if(location.getName().equals(reader.getLocation().getName()) &&
                location.getPostalCode().equals(reader.getLocation().getPostalCode())) {
                    nameLocation.setSelectedIndex(index);
                    locationFound = true;
                }
            }
            index++;
        }

        hadPaidRegistration.setSelected(reader.getHadPaidRegistration());
    }

    private Reader createReaderFromForm() {
        Integer readerNumber = null;

        if(readerToUpdate != null) {
            readerNumber = readerToUpdate.getReaderNumber();
        }

        Character genderValue = null;
        if(gender.getSelectedIndex() != 0) {
            genderValue = gender.getSelectedItem().toString().charAt(0);
        }

        String phoneValue = null;
        if(!numberPhone.getText().trim().isEmpty()) {
            phoneValue = numberPhone.getText().trim();
        }

        Location selectedLocation = (Location) nameLocation.getSelectedItem();

        Date registrationDate;

        if(readerToUpdate == null) {
            registrationDate = new Date();
        }
        else {
            registrationDate = readerToUpdate.getRegistrationDate();
        }

        return new Reader(
                readerNumber,
                lastName.getText().trim(),
                firstName.getText().trim(),
                genderValue,
                streetNumberAndName.getText().trim(),
                phoneValue,
                registrationDate,
                hadPaidRegistration.isSelected(),
                (Date) birthDate.getValue(),
                email.getText().trim(),
                selectedLocation.getName(),
                selectedLocation.getPostalCode()
        );
    }
}
