package LLDProblems.Self.HotelBookingSystem;

// ========================= EVENT BUS (OBSERVER PATTERN) ============================

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface EventListener {
    void onEvent(String eventType, Object data);
}

class EventBus {

    private static EventBus instance;

    Map<String, List<EventListener>> listeners = new HashMap<>();

    public static EventBus getInstance() {
        if (instance == null) instance = new EventBus();
        return instance;
    }

    public void subscribe(String event, EventListener l) {
        listeners.putIfAbsent(event, new ArrayList<>());
        listeners.get(event).add(l);
    }

    public void publish(String event, Object data) {
        if (!listeners.containsKey(event)) return;

        for (EventListener l : listeners.get(event)) {
            l.onEvent(event, data);
        }
    }
}

// ========================= NOTIFICATION SERVICE ============================

class NotificationService implements EventListener {

    @Override
    public void onEvent(String eventType, Object data) {

        Booking b = (Booking) data;
        User u = DataStore.getInstance().users.get(b.userId);

        switch (eventType) {
            case "BOOKING_CONFIRMED":
                System.out.println("📧 CONFIRMATION EMAIL to " + u.email + " → Booking " + b.bookingId);
                break;

            case "BOOKING_CANCELLED":
                System.out.println("📧 CANCELLATION EMAIL to " + u.email + " → Booking " + b.bookingId);
                break;

            case "PAYMENT_SUCCESS":
                System.out.println("📧 PAYMENT SUCCESS EMAIL to " + u.email + " for Booking " + b.bookingId);
                break;

            case "CHECKED_IN":
                System.out.println("📧 CHECK-IN EMAIL to " + u.email + " → Booking " + b.bookingId);
                break;

            case "CHECKED_OUT":
                System.out.println("📧 CHECK-OUT EMAIL to " + u.email + " → Booking " + b.bookingId);
                break;
        }
    }
}