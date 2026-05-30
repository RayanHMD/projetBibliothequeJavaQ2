package business;

import dao.reader.ReaderDBAccess;
import dao.reader.ReaderDataAccess;
import exception.BusinessException;
import exception.DataAccessException;
import mvc.model.Reader;

import java.util.ArrayList;
import java.util.Date;

public class ReaderManager {
    private ReaderDataAccess readerDataAccess;

    public ReaderManager() {
        this.readerDataAccess = new ReaderDBAccess();
    }

    public ArrayList<Reader> getAllReaders() throws DataAccessException {
        return readerDataAccess.getAllReaders();
    }

    public void addReader(Reader reader) throws DataAccessException, BusinessException {
        validateReader(reader);
        readerDataAccess.addReader(reader);
    }

    public void updateReader(Reader reader) throws DataAccessException, BusinessException {
        validateReader(reader);
        readerDataAccess.updateReader(reader);
    }

    public void deleteReader(Reader reader) throws DataAccessException {
        readerDataAccess.deleteReader(reader);
    }

    private void validateReader(Reader reader) throws BusinessException {
        if(reader == null) {
            throw new BusinessException("Le lecteur ne peut pas être vide.");
        }

        if(reader.getLastName() == null || reader.getLastName().trim().isEmpty()) {
            throw new BusinessException("Le nom est obligatoire.");
        }
        if(reader.getLastName().trim().length() > 100) {
            throw new BusinessException("Le nom ne peut pas dépasser 100 caractères.");
        }

        if(reader.getFirstName() == null || reader.getFirstName().trim().isEmpty()) {
            throw new BusinessException("Le prénom est obligatoire.");
        }
        if(reader.getFirstName().trim().length() > 100) {
            throw new BusinessException("Le prénom ne peut pas dépasser 100 caractères.");
        }

        Character gender = reader.getGender();
        if (gender != null && gender != 'm' && gender != 'f' && gender != 'x') {
            throw new BusinessException("Le genre doit être m, f ou x.");
        }

        if(reader.getNumberPhone() != null && reader.getNumberPhone().trim().length() > 20) {
            throw new BusinessException("Le numéro de téléphone ne peut pas dépasser 20 caractères.");
        }

        Date today = new Date();
        if(reader.getRegistrationDate() == null || reader.getRegistrationDate().after(today)) {
            throw new BusinessException("La date d'inscription est invalide.");
        }

        if(reader.getHadPaidRegistration() == null) {
            throw new BusinessException("Le paiement de l'inscription doit être précisé.");
        }

        if(reader.getBirthDate() == null || reader.getBirthDate().after(today)) {
            throw new BusinessException("La date de naissance est invalide.");
        }

        if(reader.getEmail() == null || reader.getEmail().trim().isEmpty()) {
            throw new BusinessException("L'email est obligatoire.");
        }
        if(reader.getEmail().trim().length() > 255) {
            throw new BusinessException("L'email ne peut pas dépasser 255 caractères.");
        }
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if(!reader.getEmail().trim().matches(regex)) {
            throw new BusinessException("L'email est invalide.");
        }

        if(reader.getStreetNumberAndName() == null || reader.getStreetNumberAndName().trim().isEmpty()) {
            throw new BusinessException("La rue et le numéro sont obligatoires.");
        }
        if(reader.getStreetNumberAndName().trim().length() > 150) {
            throw new BusinessException("L'adresse ne peut pas dépasser 150 caractères.");
        }

        if(reader.getLocation() == null
                || reader.getLocation().getName() == null
                || reader.getLocation().getName().trim().isEmpty()
                || reader.getLocation().getPostalCode() == null
                || reader.getLocation().getPostalCode() <= 0) {
            throw new BusinessException("La localité est obligatoire.");
        }


    }
}
