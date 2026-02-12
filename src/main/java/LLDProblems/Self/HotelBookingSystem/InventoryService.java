package LLDProblems.Self.HotelBookingSystem;

import java.time.LocalDate;

class InventoryService {

    DataStore db = DataStore.getInstance();

    boolean checkAvailability(int roomTypeId, LocalDate checkIn, LocalDate checkOut, int qty) {
        for (LocalDate d = checkIn; !d.isAfter(checkOut.minusDays(1)); d = d.plusDays(1)) {
            RoomInventory inv = db.inventories.get(roomTypeId * 10000 + d.hashCode());
            if (inv == null || inv.availableCount < qty) return false;
        }
        return true;
    }

    void reduceInventory(int roomTypeId, LocalDate checkIn, LocalDate checkOut, int qty) {
        for (LocalDate d = checkIn; !d.isAfter(checkOut.minusDays(1)); d = d.plusDays(1)) {
            RoomInventory inv = db.inventories.get(roomTypeId * 10000 + d.hashCode());
            inv.availableCount -= qty;
            inv.bookedCount += qty;
        }
    }

    void increaseInventory(int roomTypeId, LocalDate checkIn, LocalDate checkOut, int qty) {
        for (LocalDate d = checkIn; !d.isAfter(checkOut.minusDays(1)); d = d.plusDays(1)) {
            RoomInventory inv = db.inventories.get(roomTypeId * 10000 + d.hashCode());
            inv.availableCount += qty;
            inv.bookedCount -= qty;
        }
    }
}

