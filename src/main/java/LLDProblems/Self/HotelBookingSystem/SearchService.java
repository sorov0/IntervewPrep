package LLDProblems.Self.HotelBookingSystem;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

class SearchService {

    DataStore db = DataStore.getInstance();
    InventoryService inventoryService = new InventoryService();

    List<Hotel> searchHotels(int cityId, LocalDate checkIn, LocalDate checkOut) {

        return db.hotels.values().stream()
                .filter(h -> h.cityId == cityId)
                .filter(h -> hasAnyAvailableRoomType(h.hotelId, checkIn, checkOut))
                .collect(Collectors.toList());
    }

    boolean hasAnyAvailableRoomType(int hotelId, LocalDate checkIn, LocalDate checkOut) {

        Hotel h = db.hotels.get(hotelId);

        for (int roomTypeId : h.roomTypeIds) {
            if (inventoryService.checkAvailability(roomTypeId, checkIn, checkOut, 1)) {
                return true;
            }
        }
        return false;
    }

    List<RoomType> getAvailableRoomTypes(int hotelId, LocalDate checkIn, LocalDate checkOut) {

        Hotel h = db.hotels.get(hotelId);

        return h.roomTypeIds.stream()
                .map(id -> db.roomTypes.get(id))
                .filter(rt -> inventoryService.checkAvailability(rt.roomTypeId, checkIn, checkOut, 1))
                .collect(Collectors.toList());
    }
}

