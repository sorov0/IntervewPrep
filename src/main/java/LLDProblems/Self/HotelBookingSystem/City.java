package LLDProblems.Self.HotelBookingSystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

enum RoomStatus { ACTIVE, INACTIVE }
enum BookingStatus { PENDING, CONFIRMED, CANCELLED, FAILED, CHECKED_IN, COMPLETED }
enum PaymentStatus { PENDING, SUCCESS, FAILED }
enum PaymentMode { CARD, UPI, NET_BANKING, PAYPAL, WALLET }

class City {
    int cityId;
    String name;
    String state;
    String country;
}

class Amenity {
    int amenityId;
    String name;
}

class Address {
    String line1;
    String line2;
    String city;
    String pincode;
    String country;
}

class Hotel {
    int hotelId;
    int cityId;
    String name;
    Address address;
    double starRating;
    List<Amenity> amenities;
    List<Integer> roomTypeIds;
}

class RoomType {
    int roomTypeId;
    int hotelId;
    String name;
    //int capacity;
    double basePrice;
    List<Amenity> amenities;
    int totalRooms;
}

class PhysicalRoom {
    int roomId;
    int roomTypeId;
    String roomNumber;
    RoomStatus status;
}

class RoomInventory {
    int inventoryId;
    int roomTypeId;
    LocalDate date;
    int availableCount;
    int lockedCount;
    int bookedCount;
}

class Booking {
    int bookingId;
    int userId;
    int hotelId;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    BookingStatus status;
    double totalPrice;
    List<BookingRoom> bookingRooms;
}

class BookingRoom {
    int bookingRoomId;
    int bookingId;
    int roomTypeId;
    int quantity;
    double pricePerNight;
    double totalPrice;
}

class User {
    int userId;
    String name;
    String email;
    String phone;
}

class Payment {
    int paymentId;
    int bookingId;
    double amount;
    PaymentMode mode;
    PaymentStatus status;
    String transactionRef;
    LocalDateTime timestamp;
}
