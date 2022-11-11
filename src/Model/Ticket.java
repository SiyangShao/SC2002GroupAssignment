package Model;

import java.io.Serializable;

/**
 Represents a Ticket
 @version 1.0
 @since   20 October 2022
 */
public class Ticket extends Seat implements Serializable {
    /**
     * The type of the ticket
     */
    private TicketType type;
    /**
     * The price of the ticket
     */
    private double finalPrice;
    /**
     * The special price for senior
     */
    private static double senior = 0.8;
    /**
     * The special price for student
     */
    private static double student = 0.9;
    /**
     * The special price for child
     */
    private static double child = 0.5;
    /**
     * The transaction ID of the ticket
     */
    private final String transactionID;
    //private userid


    /**
     * The constructor of the ticket
     *
     * @param seatNo        The seat number of the ticket
     * @param MovieID       The Movie ID of the ticket
     * @param basePrice     The base price of the ticket
     * @param type          The type of the ticket
     * @param transactionID The transaction ID of the ticket
     */
    public Ticket(int seatNo, int MovieID, double basePrice, TicketType type, String transactionID) {
        super(seatNo, MovieID);
        this.type = type;
        this.transactionID = transactionID;
        switch (type) {
            case SENIOR_CITIZEN -> this.finalPrice = basePrice * senior;
            case STUDENT -> this.finalPrice = basePrice * student;
            case CHILD -> this.finalPrice = basePrice * child;
            default -> this.finalPrice = basePrice;
        }
    }

    /**
     * Change the discount for senior
     *
     * @param new_senior  The new discount for senior
     * @param new_student The new discount for student
     * @param new_child   The new discount for child
     */
    public static void changeDiscount(double new_senior, double new_student, double new_child) {
        senior = new_senior;
        student = new_student;
        child = new_child;
    }

    /**
     * Get the type of the ticket
     *
     * @return The type of the ticket
     */

    public TicketType getType() {
        return type;
    }

    /**
     * Set the type for ticket
     *
     * @param type The type of the ticket
     */

    public void setType(TicketType type) {
        this.type = type;
    }

    /**
     * Get the final price of the ticket
     *
     * @return The final price of the ticket
     */
    public double getFinalPrice() {
        return finalPrice;
    }

    /**
     * Set the final price of the ticket
     *
     * @param finalPrice The final price of the ticket
     */
    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    /**
     * Get the MovieID of the ticket
     *
     * @return The MovieID of the ticket
     */
    public int getMovieID() {
        return MovieID;
    }

    /**
     * Set the MovieID of the ticket
     *
     * @param movieID  The MovieID of the ticket
     */
    public void setMovieID(int movieID) {
        MovieID = movieID;
    }

    /**
     * Get the transaction ID of the ticket
     *
     * @return The transaction ID of the ticket
     */

    public String getTransactionID() {
        return transactionID;
    }
}
