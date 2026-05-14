package mvc.business;

import mvc.dataAccess.ReaderDBAccess;
import mvc.dataAccess.ReaderDataAccess;
import mvc.exception.DataAccessException;
import mvc.model.Reader;

import java.util.ArrayList;

public class ReaderManager {
    private ReaderDataAccess readerDataAccess;

    public ReaderManager() {
        this.readerDataAccess = new ReaderDBAccess();
    }

    public ArrayList<Reader> getAllReaders() throws DataAccessException {
        return readerDataAccess.getAllReaders();
    }

    public void addReader(Reader reader) throws DataAccessException {
        readerDataAccess.addReader(reader);
    }

    public void updateReader(Reader reader) throws DataAccessException {
        readerDataAccess.updateReader(reader);
    }

    public void deleteReader(Reader reader) throws DataAccessException {
        readerDataAccess.deleteReader(reader);
    }
}
