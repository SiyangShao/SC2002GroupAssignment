package Model;

public class Ticket extends Seat {
    //    private int MovieID;
    private TicketType type;
    private double finalPrice;

    private static double senior = 0.8;
    private static double student = 0.9;
    private static double child = 0.5;
    private final String transactionID;
    //private userid

    public static void changeDiscount(double new_senior, double new_student, double new_child) {
        senior = new_senior;
        student = new_student;
        child = new_child;
    }

    public Ticket(int seatNo, int MovieID, double basePrice, TicketType type, String transactionID) {
        super(seatNo, MovieID, basePrice);
        this.type = type;
        this.transactionID = transactionID;
        switch (type) {
            case SENIOR_CITIZEN -> this.finalPrice = basePrice * senior;
            case STUDENT -> this.finalPrice = basePrice * student;
            case CHILD -> this.finalPrice = basePrice * child;
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
