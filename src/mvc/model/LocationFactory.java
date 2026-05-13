package mvc.model;

import java.util.HashMap;
import java.util.Map;

public class LocationFactory {
    private static final Map<String, Location> locations = new HashMap<>();

    private LocationFactory() {
    }

    public static Location getLocation(String name, Integer postalCode) {
        String key = name.toLowerCase() + "-" + postalCode;

        if (!locations.containsKey(key)) {
            locations.put(key, new Location(name, postalCode));
        }

        return locations.get(key);
    }
}
