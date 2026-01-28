package LLDProblems.Self.HotelBookingSystem;

// ============================================================================
// 5. BOOKING MANAGER — Main Booking Logic
// ============================================================================

import java.util.Map;
import java.util.UUID;

class BookingService {

    private DataStore db = DataStore.getInstance();
    private RoomLockManager lock = RoomLockManager.getInstance();
    private PricingStrategyFactory pricingFactory = new PricingStrategyFactory();

    /**
     * Creates a PENDING booking.
     * Locks rooms to prevent parallel booking.
     */
    public Booking createBooking(
            String userId,
            String cityId,
            String hotelId,
            DateRange stay,
            Map<String, String> roomSelections // roomId -> variantId
    ) {

        Booking booking = new Booking();
        booking.bookingId = UUID.randomUUID().toString();
        booking.userId = userId;
        booking.cityId = cityId;
        booking.hotelId = hotelId;
        booking.stay = stay;
        booking.status = BookingStatus.PENDING;

        Hotel hotel = db.hotels.get(hotelId);
        PricingStrategy strategy = pricingFactory.getStrategy(hotel);

        // Lock rooms first
        for (String roomId : roomSelections.keySet()) {
            boolean locked = lock.lockRoom(roomId, userId);
            if (!locked)
                throw new RuntimeException("Room already locked: " + roomId);
        }

        // Add BookingRoom entries
        for (Map.Entry<String, String> entry : roomSelections.entrySet()) {

            String roomId = entry.getKey();
            String variantId = entry.getValue();

            PhysicalRoom room = db.rooms.get(roomId);

            RoomVariant selectedVariant = room.variants.stream()
                    .filter(v -> v.variantId.equals(variantId))
                    .findFirst().orElse(null);

            if (selectedVariant == null)
                throw new RuntimeException("Invalid Room Variant");

            BookingRoom br = new BookingRoom();
            br.physicalRoomId = roomId;
            br.roomVariantId = variantId;
            br.roomTypeId = selectedVariant.roomTypeId;
            br.priceLocked = strategy.calculatePrice(selectedVariant, stay);

            booking.bookedRooms.add(br);
            booking.totalAmount += br.priceLocked;
        }

        db.bookings.put(booking.bookingId, booking);
        return booking;
    }

    /**
     * After payment success → confirm booking.
     */
    public void confirmBooking(String bookingId) {
        Booking booking = db.bookings.get(bookingId);
        booking.status = BookingStatus.CONFIRMED;

        db.bookings.put(bookingId, booking);

        // publish event
        EventBus.publish(new BookingConfirmedEvent(bookingId));
    }

    /**
     * Cancel booking → unlock rooms.
     */
    public void cancelBooking(String bookingId) {
        Booking booking = db.bookings.get(bookingId);
        booking.status = BookingStatus.CANCELLED;

        for (BookingRoom br : booking.bookedRooms) {
            lock.unlockRoom(br.physicalRoomId);
        }

        db.bookings.put(bookingId, booking);
    }
}
