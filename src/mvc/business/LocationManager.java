package mvc.business;

import mvc.dataAccess.location.LocationDBAccess;
import mvc.dataAccess.location.LocationDataAccess;
import mvc.exception.DataAccessException;
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
