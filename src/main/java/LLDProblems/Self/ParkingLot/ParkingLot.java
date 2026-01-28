package LLDProblems.Self.ParkingLot;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

// ========================================================================================================
/*
Thought Process : Enums help avoid magic strings and allow switching logic with type safety.
 */
enum VehicleType { BIKE, CAR, TRUCK, EV, VIP }
enum SpotType { BIKE, COMPACT, LARGE, EV, VIP }
enum SpotStatus { FREE, RESERVED, OCCUPIED }
enum TicketStatus { ACTIVE, PAID, LOST }
enum PaymentMode { CASH, CARD, UPI, WALLET, QR }
enum GateType { ENTRY, EXIT }

// ========================================================================================================

class SpotMapper {

    private SpotMapper() {}  // prevent instantiation (utility class)

    public static SpotType map(VehicleType vehicleType) {

        return switch (vehicleType) {
            case BIKE       -> SpotType.BIKE;
            case CAR        -> SpotType.COMPACT;
            case TRUCK      -> SpotType.LARGE;
            case EV         -> SpotType.EV;
            case VIP        -> SpotType.VIP;
        };
    }
}

// =================================================================================================================

class Vehicle{

    private final String number;
    private final VehicleType type;

    public Vehicle(String number, VehicleType type) {
        this.number = number;
        this.type = type;
    }

    public String getNumber() { return number; }
    public VehicleType getType() { return type; }

}

/*
Thought Process : Vehicle is pure data. No behavior required = no methods except getters.
 */
// ======================================================================================================


class ParkingSpot{

    private final String spotId;
    private final SpotType type;

    private SpotStatus status;
    private Vehicle occupiedBy;

    private final ReentrantLock lock = new ReentrantLock(true);

    public ParkingSpot(String spotId, SpotType type, SpotStatus status, Vehicle occupiedBy) {
        this.spotId = spotId;
        this.type = type;
        this.status = status;
        this.occupiedBy = occupiedBy;
    }

    public boolean tryReserve() {
        lock.lock();
        try {
            if (status != SpotStatus.FREE) return false;
            status = SpotStatus.RESERVED;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void occupy(Vehicle vehicle) {
        lock.lock();
        try {
            if (status != SpotStatus.RESERVED)
                throw new IllegalStateException("Spot must be RESERVED before OCCUPIED");
            this.occupiedBy = vehicle;
            this.status = SpotStatus.OCCUPIED;
        } finally { lock.unlock(); }
    }

    public void release() {
        lock.lock();
        try {
            this.occupiedBy = null;
            this.status = SpotStatus.FREE;
        } finally { lock.unlock(); }
    }

    public SpotType getType() { return type; }
    public SpotStatus getStatus() { return status; }
    public String getSpotId() { return spotId; }

}

/*
Thought Process :
Only ParkingSpot should mutate its internal state → SRP + Encapsulation.
Concurrency safe → locking at lowest mutation point is the best.
Methods represent state transitions.
 */
// =========================================================================================================


class ParkingFloor{

    private final String floorId;
    private final Map<SpotType, List<ParkingSpot>> spotListByType;

    public ParkingFloor(String floorId) {
        this.floorId = floorId;
        this.spotListByType = new HashMap<>();
        for (SpotType type : SpotType.values()) {
            spotListByType.put(type, new ArrayList<>());
        }
    }

    public void addSpot(ParkingSpot spot) {
        spotListByType.get(spot.getType()).add(spot);
    }

    public Map<SpotType, List<ParkingSpot>> getSpotMap() {
        return spotListByType;
    }

    public String getId() { return floorId; }

    public String getFloorId() { return floorId; }
}

/*
Thought Process :
Floor has many spots → Floor manages its own list.
Grouping by type makes filtering easy.
 */
// ===============================================================================================================


class Ticket{

    private final String id;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final long entryTime;

    private TicketStatus status;

    public Ticket(String id, Vehicle vehicle, ParkingSpot spot, long entryTime) {
        this.id = id;
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = entryTime;
        this.status = TicketStatus.ACTIVE;
    }

    public void markPaid() { status = TicketStatus.PAID; }
    public void markLost() { status = TicketStatus.LOST; }

    public String getId() { return id; }
    public TicketStatus getStatus() { return status; }
    public long getEntryTime() { return entryTime; }
    public ParkingSpot getSpot() { return spot; }
    public Vehicle getVehicle() { return vehicle; }

}

/*
Thought Process :
Ticket is a pure record object of the visit
Ticket status should be owned by Ticket class, not by ParkingLot.
 */
//========================================================================================================


interface ParkingStrategy {
    ParkingSpot allocateSpot(List<ParkingFloor> floors, Vehicle vehicle);
}


class VipFirstParkingStrategy implements ParkingStrategy {

    @Override
    public ParkingSpot allocateSpot(List<ParkingFloor> floors, Vehicle vehicle) {

        SpotType target = (vehicle.getType().name().equals("VIP"))
                ? SpotType.VIP
                : SpotMapper.map(vehicle.getType());

        // First try VIP spot
        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getSpotMap().get(SpotType.VIP)) {
                if (spot.getStatus().name().equals("FREE"))
                    return spot;
            }
        }

        // Then fallback to regular spot type
        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getSpotMap().get(target)) {
                if (spot.getStatus().name().equals("FREE"))
                    return spot;
            }
        }

        return null;
    }
}


class NearestSpotParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingSpot allocateSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        SpotType type = SpotType.valueOf(vehicle.getType().name());

        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getSpotMap().get(type)) {
                if (spot.getStatus() == SpotStatus.FREE)
                    return spot;
            }
        }
        return null;
    }
}

class LowestFloorFirstParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingSpot allocateSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        SpotType type = SpotMapper.map(vehicle.getType());

        return floors.stream()
                .sorted(Comparator.comparing(ParkingFloor::getId))
                .flatMap(f -> f.getSpotMap().get(type).stream())
                .filter(s -> s.getStatus() == SpotStatus.FREE)
                .findFirst()
                .orElse(null);
    }
}

/*
Different parking rules = must not modify ParkingLot → use Strategy Pattern.
 */
// =======================================================================================================


interface PricingStrategy {
    double calculate(long entryTime, long exitTime, VehicleType type);
}

class HourlyPricingStrategy implements PricingStrategy {

    @Override
    public double calculate(long entryTime, long exitTime, VehicleType type) {

        long hours = ((exitTime - entryTime) / (1000 * 3600)) + 1;

        return switch (type) {
            case BIKE -> 10 * hours;
            case CAR -> 20 * hours;
            case EV -> 15 * hours;
            case VIP -> 50 * hours;
            case TRUCK -> 30 * hours;
        };
    }
}

class WeekendSurgePricingStrategy implements PricingStrategy {

    @Override
    public double calculate(long entryTime, long exitTime, VehicleType type) {

        double base = new HourlyPricingStrategy().calculate(entryTime, exitTime, type);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(exitTime);

        int day = cal.get(Calendar.DAY_OF_WEEK);

        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
            return base * 1.5; // 50% weekend surge
        }
        return base;
    }
}

class FlatRatePricingStrategy implements PricingStrategy {

    @Override
    public double calculate(long entryTime, long exitTime, VehicleType type) {

        return switch (type) {
            case BIKE -> 50;
            case CAR -> 100;
            case EV -> 80;
            case VIP -> 200;
            case TRUCK -> 150;
        };
    }
}

class LostTicketPricingStrategy implements PricingStrategy {
    @Override
    public double calculate(long entry, long exit, VehicleType type) {
        return 500;
    }
}

// ================================================================================================

interface PaymentStrategy {
    boolean pay(double amount);
}

class CashPayment implements PaymentStrategy {

    public boolean pay(double amount) {
        System.out.println("Paid " + amount + " via CASH");
        return true;
    }
}

class CardPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Paid " + amount + " via CARD");
        return true;
    }
}



// ====================================================================================================

class ParkingSpotManager {

    private ParkingStrategy strategy;

    public ParkingSpotManager(ParkingStrategy strategy) {
        this.strategy = strategy;
    }

    public ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        return strategy.allocateSpot(floors, vehicle);
    }

    public void setStrategy(ParkingStrategy strategy) {
        this.strategy = strategy;
    }
}

/*
MANAGERS (Facade Pattern)
 */
// ===========================================================================================================


class PriceManager {

    private PricingStrategy strategy;

    public PriceManager(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculatePrice(long entry, long exit, VehicleType type) {
        return strategy.calculate(entry, exit, type);
    }

}

// =========================================================================================================


class PaymentManager {

    public boolean pay(double amount, PaymentStrategy strategy) {
        return strategy.pay(amount);

    }
}


// ===========================================================================================================


class DisplayBoardManager {

    public void showBoard(List<ParkingFloor> floors) {
        System.out.println("=== DISPLAY BOARD ===");
        for (ParkingFloor floor : floors) {
            System.out.println("Floor " + floor.getId());
            for (SpotType t : SpotType.values()) {
                long free = floor.getSpotMap().get(t)
                        .stream()
                        .filter(s -> s.getStatus() == SpotStatus.FREE)
                        .count();
                System.out.println(t + " → " + free);
            }
        }
    }
}

// ==========================================================================================================


class ParkingLot{

    private final List<ParkingFloor> floors;
    private final Map<String, Ticket> activeTickets;


    private final ParkingSpotManager spotManager;
    private final PriceManager priceManager;
    private final PaymentManager paymentManager;

    public ParkingLot(ParkingSpotManager spotMgr, PriceManager priceMgr, PaymentManager payMgr) {
        this.spotManager = spotMgr;
        this.priceManager = priceMgr;
        this.paymentManager = payMgr;
        this.floors = new ArrayList<>();
        this.activeTickets = new HashMap<>();
    }

    public List<ParkingFloor> getFloors() { return floors; }

    // ENTRY
    public Ticket enter(Vehicle vehicle) {

        ParkingSpot spot = spotManager.findSpot(floors, vehicle);

        if (spot == null)
            throw new RuntimeException("No available spot for vehicle: " + vehicle.getNumber());

        // FIRST reserve the spot
        boolean reserved = spot.tryReserve();

        if (!reserved)
            throw new RuntimeException("Spot could not be reserved. Concurrency issue occurred.");

        // THEN occupy the spot
        spot.occupy(vehicle);

        Ticket ticket = new Ticket(
                "T" + System.nanoTime(),
                vehicle,
                spot,
                System.currentTimeMillis()
        );

        activeTickets.put(ticket.getId(), ticket);

        System.out.println("Ticket Issued: " + ticket.getId());
        return ticket;
    }

    // CALCULATE PRICE
    public double calculateAmount(String ticketId, boolean lostTicket) {

        Ticket ticket = activeTickets.get(ticketId);

        if (ticket == null)
            throw new RuntimeException("Invalid ticket!");

        if (lostTicket) {
            return priceManager.calculatePrice(0, 0, ticket.getVehicle().getType());
        }

        return priceManager.calculatePrice(
                ticket.getEntryTime(),
                System.currentTimeMillis(),
                ticket.getVehicle().getType()
        );

    }

    // EXIT
    public void exit(String ticketId, PaymentStrategy paymentStrategy, boolean lostTicket) {

        Ticket ticket = activeTickets.get(ticketId);

        if (ticket == null)
            throw new RuntimeException("Ticket Not Found!");

        double amount = calculateAmount(ticketId, lostTicket);
        System.out.println("Payable Amount: " + amount);

        boolean success = paymentManager.pay(amount, paymentStrategy);

        if (!success) {
            System.out.println("Payment Failed!");
            return;
        }

        ticket.getSpot().release();
        ticket.markPaid();

        System.out.println("Exit Successful!");

        activeTickets.remove(ticketId);
    }


}

// =============================================================================================================


class EntryGate {

    private final String gateId;
    private final DisplayBoardManager board;

    public EntryGate(String gateId, DisplayBoardManager board) {
        this.gateId = gateId;
        this.board = board;
    }

    public Ticket generateTicket(ParkingLot lot, Vehicle vehicle) {

        System.out.println("ENTRY GATE " + gateId + ": Vehicle entering → " + vehicle.getNumber());
        board.showBoard(lot.getFloors());

        return lot.enter(vehicle);
    }
}

// ============================================================================================================

class ExitGate {

    private final String gateId;
    private final DisplayBoardManager board;

    public ExitGate(String gateId, DisplayBoardManager board) {
        this.gateId = gateId;
        this.board = board;
    }

    public void processExit(
            ParkingLot lot,
            String ticketId,
            PaymentStrategy payment,
            boolean lostTicket
    ) {
        System.out.println("EXIT GATE " + gateId + ": Processing exit → " + ticketId);
        board.showBoard(lot.getFloors());
        lot.exit(ticketId, payment, lostTicket);
    }
}

// ==================================================================================================================

class AdminService {

    public void addFloor(ParkingLot lot, ParkingFloor floor) {

        lot.getFloors().add(floor);
        System.out.println("Added Floor: " + floor.getFloorId());

    }

    public void addSpot(ParkingFloor floor, ParkingSpot spot) {

        floor.addSpot(spot);
        System.out.println("Added Spot " + spot.getSpotId() +
                " to Floor " + floor.getFloorId());

    }
}

// ==============================================================================================================








