package Model;

import java.io.Serializable;

/**
 Represents a Seat
 @version 1.0
 @since   20 October 2022
 */
public class Seat implements Serializable {
    /**
     * The Seat ID of the seat
     */
    protected int seatNo;
    /**
     * The Movie ID of the seat
     */
    protected int MovieID;

//    protected double basePrice;

    /**
     * The constructor of the seat
     *
     * @param seatNo  The seat ID of the seat
     * @param MovieID The Movie ID of the seat
     */
    public Seat(int seatNo, int MovieID) {
        setSeatNo(seatNo);
        setMovieID(MovieID);
//        setBasePrice(basePrice);
    }

    /**
     * Get the seat number of the seat
     *
     * @return The seat number of the seat
     */

    public int getSeatNo() {
        return seatNo;
    }

    /**
     * Set the seat number of the seat
     *
     * @param seatNo The seat number of the seat
     */

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    /**
     * Get the Movie ID of the seat
     *
     * @return The Movie ID of the seat
     */
    public int getMovieID() {
        return MovieID;
    }

    /**
     * Set the Movie ID of the seat
     *
     * @param movieID The Movie ID of the seat
     */

    public void setMovieID(int movieID) {
        MovieID = movieID;
    }

}
