package mvc.dataAccess;

import mvc.exception.DataAccessException;
import mvc.model.Reader;

import java.util.ArrayList;

public interface ReaderDataAccess {
    ArrayList<Reader> getAllReaders() throws DataAccessException;

    void addReader(Reader reader) throws DataAccessException;
}
