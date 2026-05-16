package mvc.controller;

import mvc.business.ReaderManager;
import mvc.exception.BusinessException;
import mvc.exception.DataAccessException;
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