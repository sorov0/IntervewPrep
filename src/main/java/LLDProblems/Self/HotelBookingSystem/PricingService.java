package LLDProblems.Self.HotelBookingSystem;


import java.time.LocalDate;

class PricingService {

    DataStore db = DataStore.getInstance();

    double calculatePrice(int roomTypeId, LocalDate checkIn, LocalDate checkOut) {
        RoomType rt = db.roomTypes.get(roomTypeId);
        Hotel h = db.hotels.get(rt.hotelId);

        PricingStrategy strategy = PricingStrategyFactory.getStrategy(h);
        return strategy.calculatePrice(rt, checkIn, checkOut);
    }
}
