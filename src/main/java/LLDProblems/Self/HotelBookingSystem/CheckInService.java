package LLDProblems.Self.HotelBookingSystem;


// ============================================================================
// 7. CHECK-IN MANAGER — Handles Check-In / Check-Out Logic
// ============================================================================

import java.util.Date;
import java.util.UUID;

class CheckInService {

    DataStore db = DataStore.getInstance();

    public void checkIn(String bookingId) {
        Booking booking = db.bookings.get(bookingId);

        StayRecord sr = new StayRecord();
        sr.stayRecordId = UUID.randomUUID().toString();
        sr.bookingId = bookingId;
        sr.checkInTime = new Date();

        booking.stayRecordId = sr.stayRecordId;
        booking.status = BookingStatus.CHECKED_IN;

        db.bookings.put(bookingId, booking);

        EventBus.publish(new CheckInEvent(bookingId));
    }

    public void checkOut(String bookingId) {
        Booking booking = db.bookings.get(bookingId);
        booking.status = BookingStatus.CHECKED_OUT;

        db.bookings.put(bookingId, booking);
    }
}
