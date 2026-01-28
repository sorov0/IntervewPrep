package LLDProblems.Self.HotelBookingSystem;

// ============================================================================
// DATASTORE — Acts like BookMyShow BMSDatabase
// ============================================================================

import java.util.HashMap;
import java.util.Map;

class DataStore {

    private static DataStore instance = null;

    // City → Hotels → Rooms
    Map<String, City> cities = new HashMap<>();

    // Flat maps for quick access
    Map<String, Hotel> hotels = new HashMap<>();
    Map<String, PhysicalRoom> rooms = new HashMap<>();

    Map<String, Booking> bookings = new HashMap<>();
    Map<String, Payment> payments = new HashMap<>();

    private DataStore() {}

    public static DataStore getInstance() {
        if (instance == null) instance = new DataStore();
        return instance;
    }
}
