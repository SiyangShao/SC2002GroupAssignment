package Model;

import Controller.MovieManager;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MovieSlot {
    private LocalDateTime datetime;

    private Movie.DateType dateType;

    static private double holidayPrice = 1.2;


    private CinemaType cinemaType;

    static private double platinumPrice = 1.5;

    static private double goldPrice = 1.2;

    static private double normalPrice = 1;

    private int MovieID;
    private int CinemaID;
    private int MovieSlotID;

    private String MovieName;

    /*
    seats are unsold out tickets, and tickets are sold out seats
     */
    private final ArrayList<Seat> seats;

    private final ArrayList<Ticket> tickets;

    public MovieSlot(LocalDateTime datetime, int MOVIE, int CINEMA, int seat_numbers, double basePrice, Movie.DateType dateType, CinemaType cinemaType) {
        switch (dateType) {
            case HOLIDAY -> basePrice *= holidayPrice;
            default -> basePrice *= normalPrice;
        }
        switch (cinemaType) {
            case PLATINUM -> basePrice *= platinumPrice;
            case GOLD -> basePrice *= goldPrice;
            default -> basePrice *= normalPrice;
        }
        setDatetime(datetime);
        setMovieID(MOVIE);
        setCinemaID(CINEMA);
        setMovieName(MOVIE);
        this.seats = new ArrayList<>();
        for (int i = 0; i < seat_numbers; ++i) {
            Seat newSeat = new Seat(i, this.MovieID, basePrice);
            this.seats.add(newSeat);
        }
        this.tickets = new ArrayList<>();
        setMovieSlotID();
    }
    private void setMovieSlotID() {
        int count = 0;
        ArrayList<Movie> Movies = MovieManager.getInstance().getMovies();
        for (int i = 0; i < Movies.size(); i++) {
            count += Movies.get(i).getSlotCount();
        }
        MovieSlotID = count + 1;

    }

    public int getMovieSlotID() {
        return MovieSlotID;
    }

    private void setMovieName(int movieID) {
        this.MovieName = MovieManager.getInstance().getOneMovie(movieID).getTitle();
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int movieID) {
        MovieID = movieID;
    }

    public int getCinemaID() {
        return CinemaID;
    }

    public void setCinemaID(int cinemaID) {
        CinemaID = cinemaID;
    }

    public String getMovieName() {
        return MovieName;
    }

    public int remainSeatNumber() {
        return this.seats.size();
    }

    public void showSeats() {
        System.out.println("There are " + remainSeatNumber() + " seats left in this slot.");
        System.out.println("Remaining Seats:");
        for (Seat seat : this.seats) {
            System.out.println(seat.getSeatNo());
        }
    }

    public boolean checkSeat(int seatNo) {
        for (Seat seat : this.seats) {
            if (seat.getSeatNo() == seatNo) {
                return true;
            }
        }
        return false;
    }

    private double bookSeats(int seatNo, TicketType type, String transactionID) {
        for (Seat seat : this.seats) {
            if (seat.getSeatNo() == seatNo) {
                System.out.println("You have successfully booked seat with SeatID: " + seatNo);
                System.out.println("The type of the ticket is " + type);
                this.seats.remove(seat);
                Ticket newTicket = new Ticket(seatNo, this.MovieID, seat.getBasePrice(), type, transactionID);
                System.out.println("The final price of the ticket is " + newTicket.getFinalPrice());
                this.tickets.add(newTicket);
                return newTicket.getFinalPrice();
            }
        }
        return 0;
    }

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
        return TransactionID;
    }

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

    private void removeTicket(int seatNo) {
        for (Ticket ticket : this.tickets) {
            if (ticket.getSeatNo() == seatNo) {
                System.out.println("You have successfully removed ticket with SeatID: " + seatNo);
                this.tickets.remove(ticket);
                Seat newSeat = new Seat(seatNo, this.MovieID, ticket.getBasePrice());
                this.seats.add(newSeat);
                return;
            }
        }
    }

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
        return true;
    }
}
