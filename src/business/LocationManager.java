package business;

import dao.location.LocationDBAccess;
import dao.location.LocationDataAccess;
import exception.DataAccessException;
import mvc.model.Location;
import java.util.ArrayList;

public class LocationManager {
    private LocationDataAccess locationDataAccess;

    public LocationManager() {
        locationDataAccess = new LocationDBAccess();
    }

    public ArrayList<Location> getAllLocations() throws DataAccessException {
        return locationDataAccess.getAllLocations();
    }
}
