package LLDProblems.Self.HotelBookingSystem;

import java.time.LocalDate;
import java.util.List;

class HotelBookingSystemRunner {

    public static void main(String[] args) {

        DataStore db = DataStore.getInstance();

        // ========== Register Notification Listeners ==========
        EventBus bus = EventBus.getInstance();
        NotificationService ns = new NotificationService();

        bus.subscribe("BOOKING_CONFIRMED", ns);
        bus.subscribe("BOOKING_CANCELLED", ns);
        bus.subscribe("PAYMENT_SUCCESS", ns);
        bus.subscribe("CHECKED_IN", ns);
        bus.subscribe("CHECKED_OUT", ns);

        // ========== Create a user ==========
        User u = new User();
        u.userId = 1;
        u.name = "Saurav Kumar";
        u.email = "saurav@test.com";
        db.users.put(1, u);

        // ========== Create hotel data ==========
        Hotel h = new Hotel();
        h.hotelId = 1;
        h.cityId = 101;
        h.name = "The Grand Palace";
        h.starRating = 4.8;
        h.roomTypeIds = List.of(10);
        db.hotels.put(1, h);

        RoomType rt = new RoomType();
        rt.roomTypeId = 10;
        rt.hotelId = 1;
        rt.name = "Deluxe Room";
        rt.basePrice = 3000;
        rt.totalRooms = 20;
        db.roomTypes.put(10, rt);

        // ========== Physical Rooms ==========
        for (int i = 1; i <= 20; i++) {
            PhysicalRoom pr = new PhysicalRoom();
            pr.roomId = i;
            pr.roomTypeId = 10;
            pr.roomNumber = "DLX-" + i;
            pr.status = RoomStatus.ACTIVE;
            db.physicalRooms.put(i, pr);
        }

        // ========== Create inventory ==========
        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = checkIn.plusDays(3);

        for (LocalDate d = checkIn; !d.isAfter(checkOut.minusDays(1)); d = d.plusDays(1)) {

            RoomInventory inv = new RoomInventory();
            inv.inventoryId = rt.roomTypeId * 10000 + d.hashCode();
            inv.roomTypeId = 10;
            inv.date = d;
            inv.availableCount = 10;

            db.inventories.put(inv.inventoryId, inv);
        }

        // ====== NEW: SEARCH FLOW ======
        SearchService searchService = new SearchService();

        System.out.println("\n=========== SEARCH RESULTS ===========");

        List<Hotel> hotels = searchService.searchHotels(101, checkIn, checkOut);

        System.out.println("Hotels available in city 101:");
        for (Hotel hotel : hotels) {
            System.out.println(" → " + hotel.name);
        }

        System.out.println("\nAvailable room types in hotel -> The Grand Palace:");
        List<RoomType> availableRoomTypes =
                searchService.getAvailableRoomTypes(1, checkIn, checkOut);

        for (RoomType rt2 : availableRoomTypes) {
            System.out.println(" → " + rt2.name);
        }

        // ======= END SEARCH FLOW =======

        // ========== Create Booking ==========
        BookingManager bm = new BookingManager();
        List<RoomRequest> req = List.of(new RoomRequest(10, 2));

        Booking booking = bm.createBooking(1, 1, req, checkIn, checkOut);
        System.out.println("\nBooking Created → ID: " + booking.bookingId);
        System.out.println("Total Price: " + booking.totalPrice);

        // ========== Initiate Payment ==========
        PaymentService ps = new PaymentService();

        Payment p = ps.initiatePayment(booking.bookingId, booking.totalPrice, PaymentMode.UPI);
        ps.verifyPayment(booking.bookingId, p.paymentId);

        System.out.println("Booking Status After Payment → " + db.bookings.get(booking.bookingId).status);

        // ========== Check-In ==========
        CheckInManager cim = new CheckInManager();
        cim.checkIn(booking.bookingId);

        System.out.println("Booking Status After Check-In → " + db.bookings.get(booking.bookingId).status);

        // ========== Check-Out ==========
        cim.checkOut(booking.bookingId);
        System.out.println("Booking Status After Check-Out → " + db.bookings.get(booking.bookingId).status);
    }
}

