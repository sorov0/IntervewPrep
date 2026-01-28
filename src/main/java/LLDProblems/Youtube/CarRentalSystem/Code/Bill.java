package LLDProblems.Youtube.CarRentalSystem.Code;

public class Bill {

    Reservation reservation;
    double totalBillAmount;
    boolean isBillPaid;

    Bill(Reservation reservation) {
        this.reservation = reservation;
        this.totalBillAmount = computeInvoiceAmount();
        isBillPaid = false;
    }

    private double computeInvoiceAmount(){

        return 100.0;
    }
}
