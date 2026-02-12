package LLDProblems.Self.HotelBookingSystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

interface PricingStrategy {
    double calculatePrice(RoomType roomType, LocalDate checkIn, LocalDate checkOut);
}

class BasePricingStrategy implements PricingStrategy {
    public double calculatePrice(RoomType rt, LocalDate checkIn, LocalDate checkOut) {
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        return rt.basePrice * nights;
    }
}

class WeekendPricingStrategy implements PricingStrategy {
    public double calculatePrice(RoomType rt, LocalDate checkIn, LocalDate checkOut) {
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        double total = 0;

        for (LocalDate d = checkIn; !d.isAfter(checkOut.minusDays(1)); d = d.plusDays(1)) {
            boolean weekend = d.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    d.getDayOfWeek() == DayOfWeek.SUNDAY;

            total += weekend ? rt.basePrice * 1.2 : rt.basePrice;
        }
        return total;
    }
}

class PricingStrategyFactory
{
    static PricingStrategy getStrategy(Hotel hotel) {
        if (hotel.starRating >= 4.5) return new WeekendPricingStrategy();
        return new BasePricingStrategy();
    }

}

