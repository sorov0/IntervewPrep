package LLDProblems.Self.HotelBookingSystem;

import java.util.HashMap;
import java.util.Map;

class DataStore {

    private static DataStore instance = null;

    Map<Integer, City> cities = new HashMap<>();
    Map<Integer, Hotel> hotels = new HashMap<>();
    Map<Integer, RoomType> roomTypes = new HashMap<>();
    Map<Integer, PhysicalRoom> physicalRooms = new HashMap<>();
    Map<Integer, RoomInventory> inventories = new HashMap<>();
    Map<Integer, Booking> bookings = new HashMap<>();
    Map<Integer, Payment> payments = new HashMap<>();
    Map<Integer, User> users = new HashMap<>();

    private DataStore() {}

    public static DataStore getInstance() {
        if (instance == null) instance = new DataStore();
        return instance;
    }
}

