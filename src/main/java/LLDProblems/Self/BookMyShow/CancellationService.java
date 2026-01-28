package LLDProblems.Self.BookMyShow;

class CancellationService {

    private final CancellationStrategy strategy;

    public CancellationService(CancellationStrategy strategy) {
        this.strategy = strategy;
    }

    public double cancelBooking(Booking booking) {
        double refund = strategy.getRefundAmount(booking);
        booking.cancel();

        System.out.println("Booking cancelled. Refund: " + refund);
        return refund;
    }
}
