package LLDProblems.Self.BookMyShow;


import java.util.*;

import static LLDProblems.Self.BookMyShow.SeatType.SILVER;

enum SeatType { SILVER, GOLD, PLATINUM, RECLINER }
enum ShowSeatStatus { FREE, LOCKED, BOOKED }
enum BookingStatus { PENDING, CONFIRMED, CANCELLED }
enum PaymentMode { CARD, UPI, NET_BANKING, WALLET }
enum PaymentStatus { SUCCESS, FAILURE }
enum CancellationPolicy { TIME_BASED, FLAT_50, NO_REFUND }

class City {

    private final String id;
    private final String name;
    private final List<Theatre> theatres = new ArrayList<>();

    public City(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addTheatre(Theatre t) { theatres.add(t); }
    public List<Theatre> getTheatres() { return theatres; }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
/*
Why this class exists?
Because BookMyShow is multi-city, and all searches begin with “Select City”.
City is a pure domain object (value/entity), NOT a behavior unit.
 */

class Theatre {
    private final String theatreId;
    private final String name;
    private final City city;
    private final List<Screen> screens = new ArrayList<>();

    public Theatre(String theatreId, String name, City city) {
        this.theatreId = theatreId;
        this.name = name;
        this.city = city;
    }

    public void addScreen(Screen s) { screens.add(s); }
    public List<Screen> getScreens() { return screens; }
}


class Screen {

    private final String screenId;
    private final String name;
    private final Theatre theatre;
    private final List<Seat> seats = new ArrayList<>();

    public Screen(String screenId, String name, Theatre theatre) {
        this.screenId = screenId;
        this.name = name;
        this.theatre = theatre;
    }

    public void addSeat(Seat seat) { seats.add(seat); }
    public List<Seat> getSeats() { return seats; }
}

class Seat {
    private final String seatId;
    private final int row;
    private final int col;
    private final SeatType type;

    public Seat(String seatId, int row, int col, SeatType type) {
        this.seatId = seatId;
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public SeatType getType() { return type; }
    public String getSeatId() { return seatId; }
}

class Movie {
    private final String movieId;
    private final String title;
    private final String language;
    private final String genre;
    private final int durationMin;

    public Movie(String movieId, String title, String language, String genre, int durationMin) {
        this.movieId = movieId;
        this.title = title;
        this.language = language;
        this.genre = genre;
        this.durationMin = durationMin;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurationMin() {
        return durationMin;
    }
}


class Show {

    private final String showId;
    private final Movie movie;
    private final Screen screen;
    private final long startTime;
    private final long endTime;
    private final List<ShowSeat> showSeats = new ArrayList<>();

    public Show(String showId, Movie movie, Screen screen, long startTime, long endTime) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.endTime = endTime;

        for (Seat seat : screen.getSeats()) {
            showSeats.add(new ShowSeat(UUID.randomUUID().toString(), seat, this));
        }
    }

    public List<ShowSeat> getShowSeats() { return showSeats; }

    public String getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}

class ShowSeat {

    private final String id;
    private final Seat seat;
    private final Show show;
    private ShowSeatStatus status = ShowSeatStatus.FREE;
    private long lockedUntil;

    public ShowSeat(String id, Seat seat, Show show) {
        this.id = id;
        this.seat = seat;
        this.show = show;
    }

    public synchronized boolean lock(long durationMs) {
        if (status == ShowSeatStatus.FREE) {
            status = ShowSeatStatus.LOCKED;
            lockedUntil = System.currentTimeMillis() + durationMs;
            return true;
        }
        return false;
    }

    public synchronized boolean isLockExpired() {
        return status == ShowSeatStatus.LOCKED && System.currentTimeMillis() > lockedUntil;
    }

    public synchronized void unlock() {
        if (status == ShowSeatStatus.LOCKED) {
            status = ShowSeatStatus.FREE;
        }
    }

    public synchronized void book() {
        if (status == ShowSeatStatus.LOCKED || status == ShowSeatStatus.FREE) {
            status = ShowSeatStatus.BOOKED;
        }
    }

    public ShowSeatStatus getStatus() { return status; }

    public String getId() {
        return id;
    }

    public Seat getSeat() {
        return seat;
    }

    public Show getShow() {
        return show;
    }

    public long getLockedUntil() {
        return lockedUntil;
    }
}


// =====================================================================================================================
// =====================================================================================================================


class User {
    private final String userId;
    private final String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() { return userId; }
}


class SeatLock {
    private final Show show;
    private final Seat seat;
    private final User user;
    private final long lockUntil;

    public SeatLock(Show show, Seat seat, User user, long lockDurationMs) {
        this.show = show;
        this.seat = seat;
        this.user = user;
        this.lockUntil = System.currentTimeMillis() + lockDurationMs;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > lockUntil;
    }

    public Show getShow() { return show; }
    public Seat getSeat() { return seat; }
    public User getUser() { return user; }
}

class SeatLockManager {

    private final long lockDurationMs;
    private final Map<String, List<SeatLock>> locksByShow = new HashMap<>(); // showId → list of locks

    public SeatLockManager(long lockDurationMs) {
        this.lockDurationMs = lockDurationMs;
    }

    public synchronized void lockSeats(Show show, List<Seat> seats, User user) {
        if (!locksByShow.containsKey(show.getShowId()))
            locksByShow.put(show.getShowId(), new ArrayList<>());

        // check if any seat already locked
        for (Seat seat : seats) {
            if (isSeatLocked(show, seat))
                throw new RuntimeException("Seat already locked: " + seat.getSeatId());
        }

        // lock all
        for (Seat seat : seats) {
            locksByShow.get(show.getShowId()).add(new SeatLock(show, seat, user, lockDurationMs));
        }
    }

    public synchronized boolean isSeatLocked(Show show, Seat seat) {
        List<SeatLock> locks = locksByShow.get(show.getShowId());
        if (locks == null) return false;

        locks.removeIf(SeatLock::isExpired); // cleanup expired locks

        return locks.stream().anyMatch(l ->
                l.getSeat().getSeatId().equals(seat.getSeatId()) &&
                        !l.isExpired());
    }

    public synchronized void unlockSeats(Show show, List<Seat> seats, User user) {
        List<SeatLock> locks = locksByShow.get(show.getShowId());
        if (locks == null) return;

        locks.removeIf(l ->
                seats.contains(l.getSeat()) &&
                        l.getUser().getUserId().equals(user.getUserId()));
    }
}


// =====================================================================================================================
// =====================================================================================================================


class Booking {
    private final String bookingId;
    private final Show show;
    private final User user;
    private final List<ShowSeat> seats;
    private BookingStatus status = BookingStatus.PENDING;
    private double price;

    public Booking(String bookingId, Show show, User user, List<ShowSeat> seats) {
        this.bookingId = bookingId;
        this.show = show;
        this.user = user;
        this.seats = seats;
    }

    public void confirm(double price) {
        this.price = price;
        this.status = BookingStatus.CONFIRMED;
        seats.forEach(ShowSeat::book);
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
        // seats are freed implicitly by admin or cron
    }

    public BookingStatus getStatus() { return status; }
    public double getPrice() { return price; }
    public List<ShowSeat> getSeats() { return seats; }
    public Show getShow() { return show; }

    public User getUser() {
        return user;
    }
}


// =====================================================================================================================
// =====================================================================================================================

interface PricingStrategy {
    double calculatePrice(List<ShowSeat> seats, Show show);
}

class BasePricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(List<ShowSeat> seats, Show show) {
        double total = 0;

        for (ShowSeat ss : seats) {
            switch (ss.getSeat().getType()) {
                case SILVER -> total += 150;
                case GOLD -> total += 250;
                case PLATINUM -> total += 350;
                case RECLINER -> total += 500;
            }
        }
        return total;
    }
}

class WeekendSurgePricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(List<ShowSeat> seats, Show show) {
        double base = new BasePricingStrategy().calculatePrice(seats, show);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(show.getStartTime());

        int day = cal.get(Calendar.DAY_OF_WEEK);
        return (day == Calendar.SATURDAY || day == Calendar.SUNDAY)
                ? base * 1.3
                : base;
    }
}

// =====================================================================================================================
// =====================================================================================================================

interface CancellationStrategy {
    double getRefundAmount(Booking booking);
}

class TimeBasedCancellationStrategy implements CancellationStrategy {
    @Override
    public double getRefundAmount(Booking booking) {
        long now = System.currentTimeMillis();
        long showTime = booking.getShow().getStartTime();

        if (showTime - now >= 2 * 3600 * 1000)   // more than 2 hours
            return booking.getPrice();          // full refund

        if (showTime - now >= 1 * 3600 * 1000)
            return booking.getPrice() * 0.5;    // 50% refund

        return 0; // no refund
    }
}

class Flat50CancellationStrategy implements CancellationStrategy {
    @Override
    public double getRefundAmount(Booking booking) {
        return booking.getPrice() * 0.5;
    }
}

// =====================================================================================================================
// =====================================================================================================================


interface PaymentStrategy {
    boolean pay(double amount);
}

class CardPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Paid " + amount + " using CARD.");
        return true;
    }
}

class UpiPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Paid " + amount + " using UPI.");
        return true;
    }
}


// =====================================================================================================================

class Ticket {
    private final String ticketId;
    private final Booking booking;
    private final String qrData;

    public Ticket(Booking booking) {
        this.booking = booking;
        this.ticketId = "T" + System.nanoTime();
        this.qrData = ticketId + "_" + booking.getShow().getShowId();
    }

    public Ticket generateTicket(Booking booking) {
        return new Ticket(booking);
    }

    public String getTicketId() { return ticketId; }
}

// =====================================================================================================================
// =====================================================================================================================


interface NotificationStrategy {
    void send(String message, User user);
}

class EmailNotificationStrategy implements NotificationStrategy {

    @Override
    public void send(String message, User user) {
        System.out.println("Email sent to " + user.getUserId() + ": " + message);
    }
}

class SmsNotificationStrategy implements NotificationStrategy {

    @Override
    public void send(String message, User user) {
        System.out.println("SMS sent to " + user.getUserId() + ": " + message);
    }
}







