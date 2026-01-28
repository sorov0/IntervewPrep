package LLDProblems.Self.BookMyShow;

class TicketService {

    public Ticket generateTicket(Booking booking) {
        return new Ticket(booking);
    }
}
