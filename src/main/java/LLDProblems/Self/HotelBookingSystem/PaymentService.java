package LLDProblems.Self.HotelBookingSystem;

import java.util.UUID;

class PaymentService {

    DataStore db = DataStore.getInstance();
    PaymentGatewayFactory factory = new PaymentGatewayFactory();

    public Payment makePayment(String bookingId, PaymentMode mode, double amount) {

        Payment payment = new Payment();
        payment.paymentId = UUID.randomUUID().toString();
        payment.bookingId = bookingId;
        payment.mode = mode;
        payment.amount = amount;

        PaymentGateway gateway = factory.getGateway(mode);
        payment.status = gateway.process(payment);

        db.payments.put(payment.paymentId, payment);

        if (payment.status == PaymentStatus.SUCCESS) {
            // attach to booking
            Booking booking = db.bookings.get(bookingId);
            booking.paymentId = payment.paymentId;
            db.bookings.put(bookingId, booking);

            EventBus.publish(new PaymentSuccessEvent(bookingId));
        }

        return payment;
    }
}