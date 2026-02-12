package LLDProblems.Self.HotelBookingSystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

class BookingManager {

    DataStore db = DataStore.getInstance();
    InventoryService inventoryService = new InventoryService();
    RoomLockManager lockManager = RoomLockManager.getInstance();
    PricingService pricingService = new PricingService();

    Booking createBooking(int userId, int hotelId,
                          List<RoomRequest> roomRequests,
                          LocalDate checkIn, LocalDate checkOut) {

        // 1. Check inventory
        for (RoomRequest rr : roomRequests) {
            if (!inventoryService.checkAvailability(rr.roomTypeId, checkIn, checkOut, rr.quantity))
                throw new RuntimeException("Room not available");
        }

        // 2. Lock rooms
        for (RoomRequest rr : roomRequests) {
            boolean locked = lockManager.lockRoomType(rr.roomTypeId, checkIn, checkOut,
                    rr.quantity, userId);
            if (!locked) throw new RuntimeException("Lock failed");
        }

        // 3. Create Booking
        Booking b = new Booking();
        b.bookingId = db.bookings.size() + 1;
        b.userId = userId;
        b.hotelId = hotelId;
        b.checkInDate = checkIn;
        b.checkOutDate = checkOut;
        b.status = BookingStatus.PENDING;
        b.bookingRooms = new ArrayList<>();

        for (RoomRequest rr : roomRequests) {
            BookingRoom br = new BookingRoom();
            br.bookingRoomId = db.bookings.size() * 100 + rr.roomTypeId;
            br.bookingId = b.bookingId;
            br.roomTypeId = rr.roomTypeId;
            br.quantity = rr.quantity;

            double price = pricingService.calculatePrice(rr.roomTypeId, checkIn, checkOut);
            br.pricePerNight = price / ChronoUnit.DAYS.between(checkIn, checkOut);
            br.totalPrice = price;

            b.bookingRooms.add(br);
            b.totalPrice += price;
        }

        db.bookings.put(b.bookingId, b);
        return b;
    }

    void confirmBooking(int bookingId) {
        Booking b = db.bookings.get(bookingId);

        for (BookingRoom br : b.bookingRooms) {
            inventoryService.reduceInventory(br.roomTypeId,
                    b.checkInDate, b.checkOutDate, br.quantity);
            lockManager.unlockRoomType(br.roomTypeId);
        }

        b.status = BookingStatus.CONFIRMED;
    }

    void cancelBooking(int bookingId) {
        Booking b = db.bookings.get(bookingId);

        for (BookingRoom br : b.bookingRooms) {
            lockManager.unlockRoomType(br.roomTypeId);
        }

        b.status = BookingStatus.CANCELLED;
    }
}

class RoomRequest {
    int roomTypeId;
    int quantity;

    RoomRequest(int r, int q) {
        roomTypeId = r;
        quantity = q;
    }
}

