package mvc.controller;

import business.ReaderManager;
import exception.BusinessException;
import exception.DataAccessException;
import mvc.model.Reader;

import java.util.ArrayList;

public class ReaderController {
    private ReaderManager readerManager;

    public ReaderController() {
        readerManager = new ReaderManager();
    }

    public ArrayList<Reader> getAllReaders() throws DataAccessException {
        return readerManager.getAllReaders();
    }

    public void addReader(Reader reader) throws DataAccessException, BusinessException {
        readerManager.addReader(reader);
    }

    public void updateReader(Reader reader) throws DataAccessException, BusinessException {
        readerManager.updateReader(reader);
    }

    public void deleteReader(Reader reader) throws DataAccessException {
        readerManager.deleteReader(reader);
    }
}