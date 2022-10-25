package Model;

public class Ticket extends Seat {
    //    private int MovieID;
    private TicketType type;
    private double finalPrice;

    private String transactionID;
    //private userid

    public Ticket(int seatNo, int MovieID, double basePrice, TicketType type, String transactionID) {
        super(seatNo, MovieID, basePrice);
        this.type = type;
        this.transactionID = transactionID;
        switch (type) {
            case SENIOR_CITIZEN -> this.finalPrice = basePrice * 0.8;
            case STUDENT -> this.finalPrice = basePrice * 0.9;
            case CHILD -> this.finalPrice = basePrice * 0.5;
            default -> this.finalPrice = basePrice;
        }
    }


    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int movieID) {
        MovieID = movieID;
    }

    public String getTransactionID() {
        return transactionID;
    }
}
