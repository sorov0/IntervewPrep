package LLDProblems.Self.BookMyShow;

import java.util.List;

class BookingService {

    private final SeatLockManager seatLockManager;
    private final PricingStrategy pricingStrategy;
    private final NotificationStrategy notificationStrategy;

    public BookingService(SeatLockManager lockManager,
                          PricingStrategy pricingStrategy,
                          NotificationStrategy notificationStrategy) {
        this.seatLockManager = lockManager;
        this.pricingStrategy = pricingStrategy;
        this.notificationStrategy = notificationStrategy;
    }

    public Booking createBooking(User user, Show show, List<Seat> seats) {

        // 1️⃣ Lock seats first
        seatLockManager.lockSeats(show, seats, user);

        // 2️⃣ Prepare ShowSeat list
        List<ShowSeat> showSeats = show.getShowSeats().stream()
                .filter(ss -> seats.contains(ss.getSeat()))
                .toList();

        Booking booking = new Booking("B" + System.nanoTime(), show, user, showSeats);

        System.out.println("Booking created (PENDING): " + booking);
        return booking;
    }

    public Ticket confirmBooking(Booking booking, PaymentStrategy paymentStrategy) {

        // 3️⃣ Calculate price
        double price = pricingStrategy.calculatePrice(booking.getSeats(), booking.getShow());

        // 4️⃣ Attempt payment
        boolean paid = paymentStrategy.pay(price);
        if (!paid) throw new RuntimeException("Payment Failed!");

        // 5️⃣ Confirm booking
        booking.confirm(price);

        // 6️⃣ Generate ticket
        Ticket ticket = new Ticket(booking);

        // 7️⃣ Send notification
        notificationStrategy.send("Your ticket " + ticket.getTicketId() + " is confirmed",
                booking.getUser());

        return ticket;
    }
}
