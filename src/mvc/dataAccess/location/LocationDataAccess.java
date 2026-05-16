package mvc.dataAccess.location;

import mvc.exception.DataAccessException;
import mvc.model.Location;
import java.util.ArrayList;

public interface LocationDataAccess {
    ArrayList<Location> getAllLocations() throws DataAccessException;
}
