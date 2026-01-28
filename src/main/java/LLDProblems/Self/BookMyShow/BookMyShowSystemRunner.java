package LLDProblems.Self.BookMyShow;

/*

A typical BookMyShow system supports:
Core Functionalities:

Browse movies/shows/programs (concerts, events)
Search by city, date, language, genre
Choose theatre/venue
View showtimes
Seat selection
Dynamic seat locking (to avoid double booking)
Booking & Payment
Ticket generation
Cancel ticket (with rules)
Notifications (email/SMS)
Multi-city architecture


Demo Runner (main method)
This shows a full BMS flow:
Create city, theatre, screen, seats
Add movie
Add show
User selects seats
Lock seats
Confirm booking
Generate ticket
Apply cancellation strategy
 */


import java.util.List;

public class BookMyShowSystemRunner {

    public static void main(String[] args) {

        // ===== 1. Admin Setup =====
        AdminService admin = new AdminService();

        City city = new City("C1", "Bangalore");
        admin.addCity(city);

        Theatre th = new Theatre("T1", "PVR Koramangala", city);
        admin.addTheatreToCity(th, city);

        Screen screen1 = new Screen("S1", "Audi 1", th);
        screen1.addSeat(new Seat("A1", 1, 1, SeatType.GOLD));
        screen1.addSeat(new Seat("A2", 1, 2, SeatType.GOLD));
        screen1.addSeat(new Seat("A3", 1, 3, SeatType.PLATINUM));

        admin.addScreenToTheatre(screen1, th);

        Movie mv = new Movie("M1", "Interstellar", "English", "Sci-Fi", 160);
        admin.addMovie(mv);

        Show show = admin.addShowToScreen(
                mv,
                screen1,
                System.currentTimeMillis() + 3600_000,  // starts in 1 hour
                System.currentTimeMillis() + 3_600_000 + 7200_000
        );

        // ===== 2. User Browsing =====
        User u = new User("U1", "John");

        // ===== 3. Booking Flow =====
        SeatLockManager lockMgr = new SeatLockManager(5 * 60 * 1000); // 5 min lock
        PricingStrategy priceStrategy = new BasePricingStrategy();
        NotificationStrategy notify = new EmailNotificationStrategy();

        BookingService bookingService = new BookingService(lockMgr, priceStrategy, notify);

        List<Seat> selectedSeats = List.of(
                screen1.getSeats().get(0),
                screen1.getSeats().get(1)
        );

        Booking booking = bookingService.createBooking(u, show, selectedSeats);

        // ===== 4. Confirm Payment =====
        PaymentStrategy paymentStrategy = new CardPaymentStrategy();
        Ticket ticket = bookingService.confirmBooking(booking, paymentStrategy);

        System.out.println("Ticket Generated: " + ticket.getTicketId());

        // ===== 5. Cancellation Scenario =====
        CancellationService cancelService =
                new CancellationService(new TimeBasedCancellationStrategy());

        double refund = cancelService.cancelBooking(booking);
        System.out.println("Refund issued: " + refund);
    }
}
