package LLDProblems.Self.HotelBookingSystem;

import java.time.LocalDateTime;

class PaymentService {

    DataStore db = DataStore.getInstance();
    BookingManager bookingManager = new BookingManager();

    Payment initiatePayment(int bookingId, double amount, PaymentMode mode) {

        PaymentGateway gateway = PaymentGatewayFactory.getGateway(mode);
        PaymentResponse response = gateway.makePayment(amount);

        Payment p = new Payment();
        p.paymentId = db.payments.size() + 1;
        p.bookingId = bookingId;
        p.amount = amount;
        p.mode = mode;
        p.status = response.success ? PaymentStatus.PENDING : PaymentStatus.FAILED;
        p.transactionRef = response.transactionRef;
        p.timestamp = LocalDateTime.now();

        db.payments.put(p.paymentId, p);
        return p;
    }

    void verifyPayment(int bookingId, int paymentId) {
        Payment p = db.payments.get(paymentId);
        PaymentGateway gateway = PaymentGatewayFactory.getGateway(p.mode);

        boolean success = gateway.verifyPayment(p.transactionRef);

        if (success) {
            p.status = PaymentStatus.SUCCESS;
            bookingManager.confirmBooking(bookingId);
        } else {
            p.status = PaymentStatus.FAILED;
            bookingManager.cancelBooking(bookingId);
        }
    }
}

