package LLDProblems.Self.HotelBookingSystem;

import java.util.*;

enum RoomStatus {
    AVAILABLE,
    OCCUPIED,
    MAINTENANCE
}

enum BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    CHECKED_IN,
    CHECKED_OUT
}

enum PaymentStatus {
    PENDING,
    SUCCESS,
    FAILED
}

enum PaymentMode {
    CARD,
    UPI,
    NET_BANKING,
    WALLET
}

/**
 * Represents the date range of a hotel stay.
 * Immutable value object → guarantees consistency.
 */
class DateRange {
    private final Date start;
    private final Date end;

    public DateRange(Date start, Date end) {
        if (end.before(start))
            throw new IllegalArgumentException("End date cannot be before start date.");

        this.start = start;
        this.end = end;
    }

    // Checks if two stays overlap (used for availability)
    public boolean overlaps(DateRange other) {
        return this.start.before(other.end) && this.end.after(other.start);
    }

    // Returns number of stay nights (minimum 1)
    public long getDays() {
        long diff = end.getTime() - start.getTime();
        return Math.max(1, diff / (1000 * 60 * 60 * 24));
    }

    public Date getStart() { return start; }
    public Date getEnd() { return end; }
}

class City {
    String cityId;
    String cityName;

    // All hotels belonging to this city
    List<Hotel> hotels = new ArrayList<>();
}

class User {
    String userId;
    String name;
    String email;
    String phone;
    int loyaltyPoints;
}



class RoomType {
    String typeId;
    String typeName;

    int maxAdults;
    int maxChildren;

    String description;
}
/*
 * A hotel typically supports multiple room types.
 * A PhysicalRoom may be sold under different room types using RoomVariants.
 */

class RoomVariant {
    String variantId;

    String roomTypeId;       // references RoomType
    double pricePerNight;

    // Optional: variant-specific amenities
    List<String> amenitiesIncluded = new ArrayList<>();
}


/**
 * Represents the actual, physical room inside a hotel.
 * Contains room number, booking slots, and multiple variants.
 */
class PhysicalRoom {
    String roomId;
    String roomNumber;

    RoomStatus status = RoomStatus.AVAILABLE;

    // A room can have multiple virtual types (variants)
    List<RoomVariant> variants = new ArrayList<>();

    // Occupied date ranges
    List<DateRange> bookedSlots = new ArrayList<>();
}

/**
 * Represents a hotel belonging to a city.
 * Contains supported room types and physical rooms.
 */
class Hotel {
    String hotelId;
    String name;
    String cityId;

    String address;
    double rating;

    // Types of rooms the hotel supports (Deluxe, Suite, etc.)
    List<RoomType> supportedRoomTypes = new ArrayList<>();

    // Actual rooms present in the hotel
    List<PhysicalRoom> physicalRooms = new ArrayList<>();

    // Extra details
    List<Amenity> amenities = new ArrayList<>();
    HotelPolicy policy;
}

class Amenity {
    String id;
    String name;
    boolean chargeable;
}

/**
 * Policy of the hotel — check-in/out times and cancellation rules.
 */
class HotelPolicy {
    String checkInTime;     // e.g., "12:00 PM"
    String checkOutTime;    // e.g., "11:00 AM"

    // cancellation rules: hours_before → penalty_percentage
    Map<Integer, Double> cancellationRules;
}

/*
 * Represents each room selected during a booking.
 * Stores both room + variant + price info.
 */
class BookingRoom {
    String physicalRoomId;
    String roomVariantId;
    String roomTypeId;

    double priceLocked;  // final price at booking time
}


class Booking {
    String bookingId;

    String userId;
    String cityId;
    String hotelId;

    DateRange stay;
    BookingStatus status = BookingStatus.PENDING;

    List<BookingRoom> bookedRooms = new ArrayList<>();
    double totalAmount;

    String paymentId;
    String stayRecordId;
}

class StayRecord {
    String stayRecordId;
    String bookingId;

    Date checkInTime;
    Date checkOutTime;
}

class Payment {
    String paymentId;
    String bookingId;

    PaymentMode mode;
    PaymentStatus status = PaymentStatus.PENDING;

    double amount;
    Date createdAt = new Date();
}

/**
 * Base event class for publishing domain events like
 * booking confirmation, payment success, check-in, etc.
 */
abstract class DomainEvent {
    Date createdAt = new Date();
}

/** Fired when a booking is confirmed. */
class BookingConfirmedEvent extends DomainEvent {
    public final String bookingId;
    public BookingConfirmedEvent(String id) { this.bookingId = id; }
}

/** Fired when payment succeeds. */
class PaymentSuccessEvent extends DomainEvent {
    public final String bookingId;
    public PaymentSuccessEvent(String id) { this.bookingId = id; }
}

/** Fired when guest checks in. */
class CheckInEvent extends DomainEvent {
    public final String bookingId;
    public CheckInEvent(String id) { this.bookingId = id; }
}


// ============================================================================
// 1. ROOM LOCK MANAGER (SINGLETON) — Prevents Double Booking
// ============================================================================

class RoomLockManager {

    private static RoomLockManager instance = null;

    private Map<String, String> locks = new HashMap<>();

    private RoomLockManager() {}

    public static RoomLockManager getInstance() {
        if (instance == null) instance = new RoomLockManager();
        return instance;
    }

    public synchronized boolean lockRoom(String roomId, String userId) {
        if (locks.containsKey(roomId)) return false;
        locks.put(roomId, userId);
        return true;
    }

    public synchronized void unlockRoom(String roomId) {
        locks.remove(roomId);
    }

    public boolean isLocked(String roomId) {
        return locks.containsKey(roomId);
    }
}

// ============================================================================
// 2. PRICING STRATEGY (Strategy Pattern)
// ============================================================================

interface PricingStrategy {
    double calculatePrice(RoomVariant variant, DateRange stay);
}

class BasePricingStrategy implements PricingStrategy {
    public double calculatePrice(RoomVariant variant, DateRange stay) {
        return variant.pricePerNight * stay.getDays();
    }
}

class SurgePricingStrategy implements PricingStrategy {
    public double calculatePrice(RoomVariant variant, DateRange stay) {
        return variant.pricePerNight * 1.3 * stay.getDays();
    }
}


// Factory
class PricingStrategyFactory {
    public PricingStrategy getStrategy(Hotel hotel) {
        // You can later add conditions for surge or seasonal pricing
        return new BasePricingStrategy();
    }
}

// ============================================================================
// 3. NOTIFICATION SYSTEM — Observer Pattern
// ============================================================================

interface NotificationSubscriber {
    void onEvent(DomainEvent event);
}

class EmailNotificationService implements NotificationSubscriber {
    public void onEvent(DomainEvent event) {
        if (event instanceof BookingConfirmedEvent) {
            System.out.println("[EMAIL] Booking Confirmed: " + ((BookingConfirmedEvent) event).bookingId);
        }
        if (event instanceof PaymentSuccessEvent) {
            System.out.println("[EMAIL] Payment Successful: " + ((PaymentSuccessEvent) event).bookingId);
        }
        if (event instanceof CheckInEvent) {
            System.out.println("[EMAIL] Check-in done for booking: " + ((CheckInEvent) event).bookingId);
        }
    }
}

class SMSNotificationService implements NotificationSubscriber {
    public void onEvent(DomainEvent event) {
        if (event instanceof BookingConfirmedEvent) {
            System.out.println("[SMS] Booking Confirmed: " + ((BookingConfirmedEvent) event).bookingId);
        }
    }
}

class EventBus {
    private static List<NotificationSubscriber> subs = new ArrayList<>();

    public static void register(NotificationSubscriber sub) {
        subs.add(sub);
    }

    public static void publish(DomainEvent event) {
        for (NotificationSubscriber sub : subs) {
            sub.onEvent(event);
        }
    }
}

// ============================================================================
// 6. PAYMENT SERVICE — Strategy + Factory (Same as BMS Payment)
// ============================================================================

interface PaymentGateway {
    PaymentStatus process(Payment payment);
}

class CardGateway implements PaymentGateway {
    public PaymentStatus process(Payment payment) { return PaymentStatus.SUCCESS; }
}

class UPIGateway implements PaymentGateway {
    public PaymentStatus process(Payment payment) { return PaymentStatus.SUCCESS; }
}

class WalletGateway implements PaymentGateway {
    public PaymentStatus process(Payment payment) { return PaymentStatus.SUCCESS; }
}

// Factory Pattern
class PaymentGatewayFactory {
    public PaymentGateway getGateway(PaymentMode mode) {
        switch (mode) {
            case CARD:  return new CardGateway();
            case UPI:   return new UPIGateway();
            case WALLET:return new WalletGateway();
            default:    return new CardGateway();
        }
    }
}












