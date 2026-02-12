package LLDProblems.Self.HotelBookingSystem;

interface PaymentGateway {
    PaymentResponse makePayment(double amount);
    boolean verifyPayment(String ref);
}

class PaymentResponse {
    String transactionRef;
    boolean success;
}

class RazorpayGateway implements PaymentGateway {
    public PaymentResponse makePayment(double amount) {
        PaymentResponse pr = new PaymentResponse();
        pr.transactionRef = "RZP-" + System.nanoTime();
        pr.success = true;
        return pr;
    }
    public boolean verifyPayment(String ref) { return true; }
}

class StripeGateway implements PaymentGateway {
    public PaymentResponse makePayment(double amount) {
        PaymentResponse pr = new PaymentResponse();
        pr.transactionRef = "ST-" + System.nanoTime();
        pr.success = true;
        return pr;
    }
    public boolean verifyPayment(String ref) { return true; }
}

class PaymentGatewayFactory {
    static PaymentGateway getGateway(PaymentMode mode) {
        switch (mode) {
            case UPI:
            case WALLET:
            case CARD: return new RazorpayGateway();
            case PAYPAL: return new StripeGateway();
            default: return new RazorpayGateway();
        }
    }
}

