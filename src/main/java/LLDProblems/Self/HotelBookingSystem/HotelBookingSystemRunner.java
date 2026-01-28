package LLDProblems.Self.HotelBookingSystem;

// ============================================================================
// PART 3 — RUNNER SIMULATION (Main Class)

/*
⭐ HOTEL BOOKING SYSTEM — REQUIREMENTS SUMMARY
1) Core Domain

Operates across multiple cities

Each city has multiple hotels

Each hotel supports multiple RoomTypes (Deluxe, Suite…)

Each physical room has multiple RoomVariants (different prices/amenities)

2) Availability

Rooms store booked date ranges

Search must return only rooms free for the date range

Locked rooms must be excluded

3) Advanced Search Filters

City

Hotel

RoomType

Price range

Adults/Children capacity

Required amenities

Must support multi-city queries

4) Booking Workflow

Create booking (locks rooms)

Confirm booking (after payment)

Cancel booking (unlocks rooms)

Booking states:
PENDING → CONFIRMED → CHECKED_IN → CHECKED_OUT / CANCELLED

5) Concurrency Control

RoomLockManager (Singleton) prevents double booking

Parallel booking attempts → only one succeeds

6) Pricing

Based on RoomVariant price × stay days

Uses Strategy pattern for pricing rules

7) Payment

Supports UPI/Card/Wallet

Uses Strategy + Factory for gateways

Payment success publishes events

8) Notifications

Observer pattern

Email + SMS listeners for events
(BookingConfirmed, PaymentSuccess, CheckIn)

9) Check-in / Check-out

Creates a StayRecord

Stores check-in & check-out timestamps

Updates booking state
 */
// ============================================================================

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelBookingSystemRunner {


    public static void main(String[] args) {

        // ---------------------------
        // 1. Setup: Register Notification Subscribers
        // ---------------------------
        EventBus.register(new EmailNotificationService());
        EventBus.register(new SMSNotificationService());

        // ---------------------------
        // 2. Create DataStore Reference
        // ---------------------------
        DataStore db = DataStore.getInstance();

        // ---------------------------
        // 3. Create City
        // ---------------------------
        City city = new City();
        city.cityId = "C1";
        city.cityName = "Bangalore";

        db.cities.put(city.cityId, city);

        // ---------------------------
        // 4. Create Room Types (Deluxe, Suite)
        // ---------------------------
        RoomType deluxe = new RoomType();
        deluxe.typeId = "RT1";
        deluxe.typeName = "Deluxe";
        deluxe.maxAdults = 2;
        deluxe.maxChildren = 1;
        deluxe.description = "Deluxe room with basic amenities";

        RoomType suite = new RoomType();
        suite.typeId = "RT2";
        suite.typeName = "Suite";
        suite.maxAdults = 3;
        suite.maxChildren = 2;
        suite.description = "Spacious suite with living area";

        // ---------------------------
        // 5. Create Hotel
        // ---------------------------
        Hotel hotel = new Hotel();
        hotel.hotelId = "H1";
        hotel.name = "Blue Orchid Hotel";
        hotel.cityId = "C1";
        hotel.address = "MG Road";
        hotel.rating = 4.5;

        // attach room types
        hotel.supportedRoomTypes.add(deluxe);
        hotel.supportedRoomTypes.add(suite);

        // add hotel to city list and database
        city.hotels.add(hotel);
        db.hotels.put(hotel.hotelId, hotel);

        // ---------------------------
        // 6. Create Physical Rooms
        // ---------------------------
        PhysicalRoom r101 = new PhysicalRoom();
        r101.roomId = "R101";
        r101.roomNumber = "101";

        PhysicalRoom r102 = new PhysicalRoom();
        r102.roomId = "R102";
        r102.roomNumber = "102";

        // add to hotel
        hotel.physicalRooms.add(r101);
        hotel.physicalRooms.add(r102);

        db.rooms.put(r101.roomId, r101);
        db.rooms.put(r102.roomId, r102);

        // ---------------------------
        // 7. Add Room Variants (same room, multiple selling options)
        // ---------------------------
        RoomVariant rv1 = new RoomVariant();
        rv1.variantId = "RV1";
        rv1.roomTypeId = deluxe.typeId;
        rv1.pricePerNight = 3000;

        RoomVariant rv2 = new RoomVariant();
        rv2.variantId = "RV2";
        rv2.roomTypeId = suite.typeId;
        rv2.pricePerNight = 5000;

        r101.variants.add(rv1);
        r101.variants.add(rv2);

        // room 102 too
        RoomVariant rv3 = new RoomVariant();
        rv3.variantId = "RV3";
        rv3.roomTypeId = deluxe.typeId;
        rv3.pricePerNight = 3200;

        r102.variants.add(rv3);

        // ---------------------------
        // 8. Create a User
        // ---------------------------
        User user = new User();
        user.userId = "U1";
        user.name = "Rahul Sharma";
        user.email = "rahul@example.com";

        // ---------------------------
        // 9. Search Available Rooms (InventoryService)
        // ---------------------------
        InventoryService inventory = new InventoryService();
        DateRange stay = new DateRange(new Date(), new Date(System.currentTimeMillis() + 86400000L)); // 1 night

        System.out.println("Searching available rooms...");
        List<PhysicalRoom> available = inventory.searchAvailableRooms("C1", stay);

        for (PhysicalRoom pr : available) {
            System.out.println("Available Room: " + pr.roomId + " (" + pr.roomNumber + ")");
            for (RoomVariant rv : pr.variants) {
                System.out.println("  → Variant " + rv.variantId + " [" + rv.roomTypeId + "] Price: ₹" + rv.pricePerNight);
            }
        }

        // ---------------------------
        // 10. Create Booking (BookingManager)
        // ---------------------------
        BookingService bookingManager = new BookingService();

        // Pick Room101 → Suite RV2
        Map<String, String> roomSelections = new HashMap<>();
        roomSelections.put("R101", "RV2");

        Booking booking = bookingManager.createBooking(
                user.userId,
                city.cityId,
                hotel.hotelId,
                stay,
                roomSelections
        );

        System.out.println("\nBooking Created:");
        System.out.println("Booking ID: " + booking.bookingId);
        System.out.println("Total Amount: ₹" + booking.totalAmount);

        // ---------------------------
        // 11. Payment (PaymentService)
        // ---------------------------
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.makePayment(
                booking.bookingId,
                PaymentMode.UPI,
                booking.totalAmount
        );

        System.out.println("\nPayment Done:");
        System.out.println("Payment ID: " + payment.paymentId);
        System.out.println("Status: " + payment.status);

        // ---------------------------
        // 12. Confirm Booking
        // ---------------------------
        bookingManager.confirmBooking(booking.bookingId);

        System.out.println("\nBooking confirmed!");

        // ---------------------------
        // 13. Check-In
        // ---------------------------
        CheckInService checkInManager = new CheckInService();
        checkInManager.checkIn(booking.bookingId);

        System.out.println("\nUser checked in!");

        // ---------------------------
        // 14. Check-Out
        // ---------------------------
        checkInManager.checkOut(booking.bookingId);

        System.out.println("\nUser checked out!");

        // ---------------------------
        // 15. Display Final Booking State
        // ---------------------------
        Booking finalBooking = db.bookings.get(booking.bookingId);

        System.out.println("\n===== FINAL BOOKING SUMMARY =====");
        System.out.println("Booking ID: " + finalBooking.bookingId);
        System.out.println("Status: " + finalBooking.status);
        System.out.println("StayRecord: " + finalBooking.stayRecordId);
        System.out.println("Payment: " + finalBooking.paymentId);
    }
}
