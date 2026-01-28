package LLDProblems.Self.ParkingLot;

// Parking Lot has multiple floors.
// It has multiple entry gates and exit gates
// It should have multiple payment methods (Payment Manager -> Payment strategies) at the exit
// There should be multiple methods of parking (Parking Spot Manager -> Parking strategies) while parking
// It should have multiple type of spots to park different type of vehicles
// Ticket should be generated at entry gate and payment should be done at exit gate
// There should be multiple ways of Price Calculation (Price Manager -> Price Strategies)
// Display Board showing available spots per floor
// Display Board at all entry and exit Gates showing all available spots per floor based on category type
// Add AdminService to add floors/spots dynamically
// Concurrency-safe spot reservation (FREE → RESERVED → OCCUPIED)
// Prevent double parking (same ticket used twice)
// Lost ticket handling
// Reserved spots / VIP

public class ParkingLotSystemRunner {


    public static void main(String[] args) throws InterruptedException {

        // Managers
        ParkingSpotManager spotManager = new ParkingSpotManager(new LowestFloorFirstParkingStrategy());

        PriceManager priceManager = new PriceManager(new HourlyPricingStrategy());

        PaymentManager paymentManager = new PaymentManager();

        ParkingLot lot = new ParkingLot(spotManager, priceManager, paymentManager);

        DisplayBoardManager board = new DisplayBoardManager();
        AdminService admin = new AdminService();

        // Setup Floors
        ParkingFloor f1 = new ParkingFloor("F1");
        admin.addSpot(f1, new ParkingSpot("S1", SpotType.COMPACT, SpotStatus.FREE, null));
        admin.addSpot(f1, new ParkingSpot("S2", SpotType.VIP, SpotStatus.FREE, null));
        admin.addSpot(f1, new ParkingSpot("S3", SpotType.EV, SpotStatus.FREE, null));

        admin.addFloor(lot, f1);

        // Gates
        EntryGate entryGate = new EntryGate("E1", board);
        ExitGate exitGate = new ExitGate("X1", board);

        // Vehicle Entry
        Vehicle vehicle = new Vehicle("KA01AB1234", VehicleType.VIP);
        Ticket ticket = entryGate.generateTicket(lot, vehicle);

        Thread.sleep(2000); // simulate parking

        // Vehicle Exit
        exitGate.processExit(lot, ticket.getId(), new CashPayment(), false);


    }
}
