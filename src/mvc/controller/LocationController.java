package mvc.controller;

import mvc.business.LocationManager;
import mvc.exception.DataAccessException;
import mvc.model.Location;

import java.util.ArrayList;

public class LocationController {
    private LocationManager locationManager;

    public LocationController() {
        locationManager = new LocationManager();
    }

    public ArrayList<Location> getAllLocations() throws DataAccessException {
        return locationManager.getAllLocations();
    }
}
