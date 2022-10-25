package Model;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MovieSlot {
    private LocalDateTime datetime;
    private int MovieID;
    private int CinemaID;
    /*
    seats are unsold out tickets, and tickets are sold out seats
     */
    private ArrayList<Seat> seats;

    private ArrayList<Ticket> tickets;

    public MovieSlot(LocalDateTime datetime, int MOVIE, int CINEMA, int seat_numbers, double basePrice) {
        setDatetime(datetime);
        setMovieID(MOVIE);
        setCinemaID(CINEMA);
        this.seats = new ArrayList<>();
        for (int i = 0; i < seat_numbers; ++i) {
            Seat newSeat = new Seat(i, this.MovieID, basePrice);
            this.seats.add(newSeat);
        }
        this.tickets = new ArrayList<>();
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

    public int remainSeatNumber() {
        return this.seats.size();
    }
}
