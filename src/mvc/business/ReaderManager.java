package mvc.business;

import mvc.dataAccess.reader.ReaderDBAccess;
import mvc.dataAccess.reader.ReaderDataAccess;
import mvc.exception.BusinessException;
import mvc.exception.DataAccessException;
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
            throw new BusinessException("Le lecteur ne peut pas etre vide.");
        }

        if(reader.getFirstName() == null || reader.getFirstName().trim().isEmpty()) {
            throw new BusinessException("Le prenom est obligatoire.");
        }
        if(reader.getLastName() == null || reader.getLastName().trim().isEmpty()) {
            throw new BusinessException("Le nom est obligatoire.");
        }
        if(reader.getEmail() == null || reader.getEmail().trim().isEmpty()) {
            throw new BusinessException("L'email est obligatoire.");
        }

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if(!reader.getEmail().matches(regex)) {
            throw new BusinessException("L'email est invalide.");
        }

        Date today = new Date();
        if(reader.getBirthDate() == null || reader.getBirthDate().after(today)) {
            throw new BusinessException("La date de naissance est invalide.");
        }

        if(reader.getRegistrationDate() == null || reader.getRegistrationDate().after(today)) {
            throw new BusinessException("La date d'inscription est invalide.");
        }

        if(reader.getLocation() == null) {
            throw new BusinessException("La localite est obligatoire.");
        }

        Character gender = reader.getGender();
        if (gender != null && gender != 'M' && gender != 'F' && gender != 'X') {
            throw new BusinessException("Le genre doit etre m, f ou x.");
        }
    }
}
