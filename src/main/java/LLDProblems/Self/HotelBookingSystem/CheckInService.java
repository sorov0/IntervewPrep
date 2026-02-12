package LLDProblems.Self.HotelBookingSystem;

// ========================= CHECK-IN MANAGER ============================

import java.util.List;
import java.util.stream.Collectors;

class CheckInManager {

    DataStore db = DataStore.getInstance();

    void checkIn(int bookingId) {

        Booking b = db.bookings.get(bookingId);

        if (b.status != BookingStatus.CONFIRMED)
            throw new RuntimeException("Cannot check-in. Booking not confirmed.");

        for (BookingRoom br : b.bookingRooms) {

            List<PhysicalRoom> rooms = db.physicalRooms.values().stream()
                    .filter(r -> r.roomTypeId == br.roomTypeId && r.status == RoomStatus.ACTIVE)
                    .limit(br.quantity)
                    .collect(Collectors.toList());

            if (rooms.size() < br.quantity)
                throw new RuntimeException("Not enough physical rooms!");

            for (PhysicalRoom pr : rooms) {
                pr.status = RoomStatus.INACTIVE;
            }
        }

        b.status = BookingStatus.CHECKED_IN;

        EventBus.getInstance().publish("CHECKED_IN", b);
    }

    void checkOut(int bookingId) {

        Booking b = db.bookings.get(bookingId);

        if (b.status != BookingStatus.CHECKED_IN)
            throw new RuntimeException("Cannot check-out. Guest not checked-in.");

        for (BookingRoom br : b.bookingRooms) {

            db.physicalRooms.values().stream()
                    .filter(r -> r.roomTypeId == br.roomTypeId && r.status == RoomStatus.INACTIVE)
                    .limit(br.quantity)
                    .forEach(r -> r.status = RoomStatus.ACTIVE);
        }

        b.status = BookingStatus.COMPLETED;

        EventBus.getInstance().publish("CHECKED_OUT", b);
    }
}
