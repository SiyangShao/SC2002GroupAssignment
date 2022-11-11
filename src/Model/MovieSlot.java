package Model;

import Controller.CalendarManager;
import Controller.MovieManager;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 Represents a Movie time slot
 @version 1.0
 @since   20 October 2022
 */
public class MovieSlot implements Serializable {
    /**
     * The date and time of the movie slot
     */
    private LocalDateTime datetime;
    /**
     * The discount for platinum cinema
     */

    static private double platinumPrice = 1.5;
    /**
     * The discount for gold cinema
     */

    static private double goldPrice = 1.2;
    /**
     * The discount for normal cinema
     */
    static private double normalPrice = 1;
    /**
     * The price for holiday
     */
    static private double HolidayPrice = 1.5;
    /**
     * The movie ID
     */
    private int MovieID;
    /**
     * The cinema ID
     */
    private int CinemaID;
    /**
     * The MovieSlot ID
     */
    private int MovieSlotID;
    /**
     * The base price of the movie
     */

    private double basePrice;
    /**
     * The movie name
     */
    private String MovieName;

    /**
     * unsold seats
     */
    private final ArrayList<Seat> seats;
    /**
     * sold tickets
     */

    private final ArrayList<Ticket> tickets;

    /**
     * The constructor of the movie slot
     *
     * @param datetime     The date and time of the movie slot
     * @param MOVIE        The movie of the movie slot
     * @param CINEMA       The cinema of the movie slot
     * @param seat_numbers The seat numbers of the movie slot
     * @param basePrice    The base price of the movie slot
     * @param dateType     The date type of the movie slot
     * @param cinemaType   The cinema type of the movie slot
     */

    public MovieSlot(LocalDateTime datetime, int MOVIE, int CINEMA, int seat_numbers, double basePrice, DateType dateType, CinemaType cinemaType) {
        double holidayPrice = 1.2;
        if (dateType == DateType.HOLIDAY) {
            basePrice *= holidayPrice;
        } else {
            basePrice *= normalPrice;
        }
        switch (cinemaType) {
            case PLATINUM -> basePrice *= platinumPrice;
            case GOLD -> basePrice *= goldPrice;
            default -> basePrice *= normalPrice;
        }
        if (CalendarManager.getInstance().isHoliday(datetime)) {
            basePrice *= HolidayPrice;
        }
        setDatetime(datetime);
        setMovieID(MOVIE);
        setCinemaID(CINEMA);
        setMovieName(MOVIE);
        setBasePrice(basePrice);
        setMovieSlotID();
        this.seats = new ArrayList<>();
        for (int i = 0; i < seat_numbers; ++i) {
            Seat newSeat = new Seat(i, this.MovieID);
            this.seats.add(newSeat);
        }
        this.tickets = new ArrayList<>();
        MovieManager.getInstance().Save();
    }

    /**
     * Set base price of the movie slot
     *
     * @param type  The type of the date and time
     * @param price The price of the movie slot
     */
    public void setPrice(CinemaType type, double price) {
        switch (type) {
            case PLATINUM -> platinumPrice = price;
            case GOLD -> goldPrice = price;
            default -> normalPrice = price;
        }
    }

    /**
     * Set MovieSlotID
     */
    private void setMovieSlotID() {
        int count = 0;
        ArrayList<Movie> Movies = MovieManager.getInstance().getMovies();
        for (Movie movie : Movies) {
            count += movie.getSlotCount();
        }
        MovieSlotID = count + 1;
        MovieManager.getInstance().Save();
    }

    /**
     * Set the basePrice of the movie slot
     *
     * @param basePrice The basePrice of the movie slot
     */

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Get the basePrice of the movie slot
     *
     * @return The basePrice of the movie slot
     */

    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Get the MovieSlotID
     *
     * @return The MovieSlotID
     */
    public int getMovieSlotID() {
        return MovieSlotID;
    }

    /**
     * set the Movie name
     *
     * @param movieID The movie ID
     */
    private void setMovieName(int movieID) {
        this.MovieName = MovieManager.getInstance().getOneMovie(movieID).getTitle();
    }

    /**
     * Get the movie date and time
     *
     * @return The movie date and time
     */
    public LocalDateTime getDatetime() {
        return datetime;
    }

    /**
     * Set the movie date and time
     *
     * @param datetime The movie date and time
     */
    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    /**
     * Get the movie ID
     *
     * @return The movie ID
     */
    public int getMovieID() {
        return MovieID;
    }

    /**
     * set the movie ID
     *
     * @param movieID The movie ID
     */

    public void setMovieID(int movieID) {
        MovieID = movieID;
    }

    /**
     * Get the cinema ID
     *
     * @return The cinema ID
     */

    public int getCinemaID() {
        return CinemaID;
    }

    /**
     * Set the cinema ID
     *
     * @param cinemaID The cinema ID
     */

    public void setCinemaID(int cinemaID) {
        CinemaID = cinemaID;
    }

    /**
     * get the movie name
     *
     * @return The movie name
     */

    public String getMovieName() {
        return MovieName;
    }

    /**
     * Get remained seat number
     *
     * @return get remained seat number
     */

    public int remainSeatNumber() {
        return this.seats.size();
    }

    /**
     * Show seats
     */

    public void showSeats() {
        int seatCount = 0;      // To keep track which seats are taken
        System.out.println("There are " + remainSeatNumber() + " seats left in this slot.");
        System.out.println("Please choose a seat from the following seating plan:");
        for (Seat seat : this.seats) {
            // If the seat number is taken, print "[ XX ]" instead
            while (seat.getSeatNo() != seatCount++) {
                System.out.printf("[ XX ] ");
            }

            // If seat number + 1 is more than 4, leave a space for the aisle, except for the first row
            if ((seat.getSeatNo() + 1) % 4 == 1 && seat.getSeatNo() != 0) {
                System.out.printf("\t");
            }

            // If seat number + 1 is more than 8, go to the next row, except for the first row
            if ((seat.getSeatNo() + 1) % 8 == 1 && seat.getSeatNo() != 0) {
                System.out.println();
            }

            // If single digit, make into double-digit
            if ((seat.getSeatNo() + 1) < 10) {
                System.out.printf("[ 0" + (int) (seat.getSeatNo() + 1) + " ] ");
                continue;
            }


            System.out.printf("[ " + (int) (seat.getSeatNo() + 1) + " ] ");
        }
        System.out.println();
    }

    /**
     * Check if the seat is available
     *
     * @param seatNo The seat number
     * @return True if the seat is available, false otherwise
     */
    public boolean checkSeat(int seatNo) {
        for (Seat seat : this.seats) {
            if (seat.getSeatNo() == seatNo) {
                return true;
            }
        }
        return false;
    }

    /**
     * Book a seat
     *
     * @param seatNo        The seat number
     * @param type          The type of the ticket
     * @param transactionID The transaction ID
     * @return Ticket Price
     */
    private double bookSeats(int seatNo, TicketType type, String transactionID) {
        for (Seat seat : this.seats) {
            if (seat.getSeatNo() == seatNo) {
                System.out.println("You have successfully booked seat with SeatID: " + seatNo);
                System.out.println("The type of the ticket is " + type);
                this.seats.remove(seat);
                Ticket newTicket = new Ticket(seatNo, this.MovieID, getBasePrice(), type, transactionID);
                System.out.println("The final price of the ticket is " + newTicket.getFinalPrice());
                this.tickets.add(newTicket);
                MovieManager.getInstance().Save();
                return newTicket.getFinalPrice();
            }
        }
        return 0;
    }

    /**
     * Book tickets
     *
     * @param seatsNo The seats number
     * @param types   The types of the tickets
     * @return Transaction ID
     */
    public String bookSeats(ArrayList<Integer> seatsNo, ArrayList<TicketType> types) {
        String bookingTime = LocalDateTime.now().toString();
        String TransactionID = this.CinemaID + "#" + this.MovieID + "#" + this.datetime + "#" + bookingTime;
        if (seatsNo.size() != types.size()) {
            System.out.println("The number of seats and types do not match.");
            return null;
        }
        boolean success = true;
        for (Integer integer : seatsNo) {
            if (!checkSeat(integer)) {
                System.out.println("Seat " + integer + " is not available.");
                success = false;
            }
        }
        if (!success) {
            System.out.println("The Transaction is cancelled.");
            return null;
        }
        double totalPrice = 0;
        for (int i = 0; i < seatsNo.size(); ++i) {
            totalPrice += bookSeats(seatsNo.get(i), types.get(i), TransactionID);
        }
        System.out.println("You have successfully booked " + seatsNo.size() + " seats.");
        System.out.println("The transaction ID is " + TransactionID);
        System.out.println("The total price is " + totalPrice);
        MovieManager.getInstance().Save();
        return TransactionID;
    }

    /**
     * Show Transaction history
     *
     * @param TransactionID The transaction ID
     */
    public void showTransactionHistory(String TransactionID) {
        System.out.println("The detail of " + TransactionID + " is:");
        String[] transactionIDSplit = TransactionID.split("#");
        // transactionIDSplit[0] is the cinema ID
        // transactionIDSplit[1] is the movie ID
        // transactionIDSplit[2] is the movie slot time
        // transactionIDSplit[3] is the booking time
        System.out.println("Booking Time: " + transactionIDSplit[3]);
        System.out.println("Cinema ID: " + transactionIDSplit[0]);
        System.out.println("Movie ID: " + transactionIDSplit[1]);
        System.out.println("Movie Slot Time: " + transactionIDSplit[2]);
        for (Ticket ticket : this.tickets) {
            if (ticket.getTransactionID().equals(TransactionID)) {
                System.out.println("Seat No: " + ticket.getSeatNo());
                System.out.println("Ticket Type: " + ticket.getType());
                System.out.println("Final Price: " + ticket.getFinalPrice());
            }
        }
    }

    /**
     * Remove a ticket
     *
     * @param seatNo The seat number
     */
    private void removeTicket(int seatNo) {
        for (Ticket ticket : this.tickets) {
            if (ticket.getSeatNo() == seatNo) {
                System.out.println("You have successfully removed ticket with SeatID: " + seatNo);
                this.tickets.remove(ticket);
                Seat newSeat = new Seat(seatNo, this.MovieID);
                this.seats.add(newSeat);
                MovieManager.getInstance().Save();
                return;
            }
        }
    }

    /**
     * Remove ticket
     *
     * @param transactionID The transaction ID
     * @return True if the transaction is removed, false otherwise
     */
    public boolean removeTicket(String transactionID) {
        ArrayList<Integer> seatNoList = new ArrayList<>();
        for (Ticket ticket : this.tickets) {
            if (ticket.getTransactionID().equals(transactionID)) {
                seatNoList.add(ticket.getSeatNo());
            }
        }
        if (seatNoList.isEmpty()) {
            System.out.println("TransactionID: " + transactionID + " doesn't exist.");
            return false;
        }
        for (int seatNo : seatNoList) {
            removeTicket(seatNo);
        }
        MovieManager.getInstance().Save();
        return true;
    }

    /**
     * Get the showtime
     *
     * @return The showtime
     */
    public LocalDateTime getShowTime() {
        return this.datetime;
    }
}
