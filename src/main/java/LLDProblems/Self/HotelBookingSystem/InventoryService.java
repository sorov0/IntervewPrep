package LLDProblems.Self.HotelBookingSystem;

import java.util.ArrayList;
import java.util.List;

class InventoryService {

    DataStore db = DataStore.getInstance();
    RoomLockManager lockManager = RoomLockManager.getInstance();

    /**
     * Returns all available physical rooms in a city for the given date range.
     */
    public List<PhysicalRoom> searchAvailableRooms(String cityId, DateRange stay) {

        City city = db.cities.get(cityId);
        List<PhysicalRoom> available = new ArrayList<>();

        for (Hotel hotel : city.hotels) {
            for (PhysicalRoom room : hotel.physicalRooms) {

                if (lockManager.isLocked(room.roomId)) continue;

                boolean free = true;

                for (DateRange slot : room.bookedSlots) {
                    if (slot.overlaps(stay)) {
                        free = false;
                        break;
                    }
                }

                if (free) available.add(room);
            }
        }
        return available;
    }
}
