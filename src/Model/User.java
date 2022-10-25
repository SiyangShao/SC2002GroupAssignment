package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Objects;

public class User implements Serializable {

    private String UserId;
    private String Email;
    private String Name;
    private String Phone;

    private ArrayList<String> TransactionID;

    public User(String UserId, String Email, String Name, String Phone) {
        this.UserId = UserId;
        this.Email = Email;
        this.Name = Name;
        this.Phone = Phone;
        this.TransactionID = new ArrayList<>();
    }

    public String getUserId() {
        return UserId;
    }

    public String getEmail() {
        return Email;
    }

    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    private void viewBookingHistory(String TransactionID, MovieSlot movieSlot) {
        movieSlot.showTransactionHistory(TransactionID);
    }

    public void viewBookingHistory(ArrayList<Movie> AllMovies) {
        System.out.println("Booking History: (shown in transaction ID)");
        for (String transactionID : TransactionID) {
            System.out.println(transactionID);
            // using # to divide string transactionID
            String[] transactionIDSplit = transactionID.split("#");
            // transactionIDSplit[0] is the cinema ID
            // transactionIDSplit[1] is the movie ID
            // transactionIDSplit[2] is the movie slot time
            // transactionIDSplit[3] is the booking time
            int cinemaID = Integer.parseInt(transactionIDSplit[0]);
            int movieID = Integer.parseInt(transactionIDSplit[1]);
            LocalDateTime movieSlotTime = LocalDateTime.parse(transactionIDSplit[2]);
            for (Movie movie : AllMovies) {
                if (movie.getMovieID() == movieID) {
                    ArrayList<MovieSlot> currentSlots = movie.getSlots(cinemaID);
                    for (MovieSlot movieSlot : currentSlots) {
                        if (Objects.equals(movieSlot.getDatetime(), movieSlotTime)) {
                            viewBookingHistory(transactionID, movieSlot);
                        }
                    }
                }
            }
        }
    }

    public boolean bookSeats(ArrayList<Integer> seatsNo, ArrayList<TicketType> types, MovieSlot movieSlot) {
        String TransactionID = movieSlot.bookSeats(seatsNo, types);
        if (TransactionID == null) return false;
        this.TransactionID.add(TransactionID);
        return true;
    }

    public boolean cancelBooking(String TransactionID, MovieSlot movieSlot) {
        if (!this.TransactionID.contains(TransactionID)) {
            System.out.println("You do not have this transaction.");
            return false;
        }
        return movieSlot.removeTicket(TransactionID);
    }
}
