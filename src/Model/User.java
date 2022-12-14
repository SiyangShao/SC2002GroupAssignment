package Model;

import Controller.MovieManager;
import Controller.UserManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

/**
 Represents a external User in Movie Booking application
 A user can have multiple transactionID and cancelled transactions
 @version 1.0
 @since   20 October 2022
 */
public class User implements Serializable {

    /**
     * System generated userID for ease usage
     */
    private int UserId;

    /**
     * Email of the user
     */
    private String Email;

    /**
     * Name of the user
     */
    private String Name;

    /**
     * Phone of the user
     */
    private String Phone;

    /**
     * List of transactionIDs that user made
     */
    private ArrayList<String> TransactionID;

    /**
     * List of tranactionIds cancelled by user
     */
    private ArrayList<String> CancelledTransactionID;

    /**
     * Creates a new User with the given UserId, Email, Name, Phone
     * @param UserId This User's UserId, generated by Manager.
     * @param Email  This User's Email.
     * @param Name  This User's Name.
     * @param Phone  This User's Phone.
     */
    public User(int UserId, String Email, String Name, String Phone) {
        this.UserId = UserId;
        this.Email = Email;
        this.Name = Name;
        this.Phone = Phone;
        this.TransactionID = new ArrayList<>();
        this.CancelledTransactionID = new ArrayList<>();
        UserManager.getInstance().Save();
    }

    /**
     * Gets the UserId of this user.
     * @return this User's UserId.
     */
    public int getUserId() {
        return UserId;
    }

    /**
     * Gets the Email of this user.
     * @return this User's Email.
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Gets the Name of this user.
     * @return this User's Name.
     */
    public String getName() {
        return Name;
    }

    /**
     * Gets the Phone of this user.
     * @return this User's Phone.
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * Changes the email of this User.
     * @param email This User's new email.
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * Changes the name of this User.
     * @param name This User's new name.
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Changes the phone of this User.
     * @param phone This User's new phone.
     */
    public void setPhone(String phone) {
        Phone = phone;
    }

    /**
     * View booking history of the user of a transaction ID
     * @param TransactionID This User's transactionID.
     * @param movieSlot The movieSlot object
     * @param movie The movie object
     */
    private void viewBookingHistory(String TransactionID, MovieSlot movieSlot, Movie movie) {
        movieSlot.showTransactionHistory(TransactionID);
    }

    /**
     * Set movie ranking for a movie
     * @param movie movie object for setting rating
     */
    public void setMovieRating(Movie movie){
    	String comment;
    	double rating = 0;
        Scanner reviewScanner = new Scanner(System.in);
        try{
            while(true){
                System.out.print("Any comments? (Type '0' to leave blank): ");
                String reviewComment = reviewScanner.nextLine();
                String end = new String("0");
                String empty = new String("NA");
                if (reviewComment.equals(end))
                {
                    comment = "";
                }
                else
                {
                    comment = reviewComment;  
                }
                
                System.out.print("Leave a review: (1-5, 0 to do it next time.): ");
                double reviewRating = reviewScanner.nextDouble();
                if (reviewRating == 0) {
                    break;
                }
                else if (reviewRating <= 5.0 || reviewRating >= 1.0){
                    rating = reviewRating;
                    break;
                }
                else{
                    continue;
                }
            }
            System.out.println("COMMENT : " + comment);
            movie.addMovieReview(comment,rating, this.UserId);
            MovieManager.getInstance().Save();
        }
        catch (Exception err){
            System.err.println(err);
        }
    }

    /**
     * View booking history
     * for all movies
     */
    public void viewBookingHistory() {
        System.out.println("Booking History: (shown in transaction ID)");
        if(TransactionID.isEmpty()){
            System.out.println("No booking history");
            return;
        }
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
            for (Movie movie : MovieManager.getInstance().getMovies()) {
                if (movie.getMovieID() == movieID) {
                    ArrayList<MovieSlot> currentSlots = movie.getSlots(cinemaID);
                    for (MovieSlot movieSlot : currentSlots) {
                        if (Objects.equals(movieSlot.getDatetime(), movieSlotTime)) {
                            viewBookingHistory(transactionID, movieSlot, movie);
                        }
                    }
                }
            }
        }
        for (String cancelledTransaction : CancelledTransactionID) {
            System.out.println("You have cancelled the transaction: " + cancelledTransaction);
        }
    }

    /**
     * book the respective seat
     * @param seatsNo List of seat numbers
     * @param types list of seat types
     * @param movieSlot movieSlot object that user is going to book in
     * @return true if successfully booked the seat, else false
     */
    public boolean bookSeats(ArrayList<Integer> seatsNo, ArrayList<TicketType> types, MovieSlot movieSlot) {
        String TransactionID = movieSlot.bookSeats(seatsNo, types);
        if (TransactionID == null) return false;
        this.TransactionID.add(TransactionID);
        UserManager.getInstance().Save();
        return true;
    }

    /**
     * Cancel a booking
     * @param TransactionID transaction id to be cancelled
     * @param movieSlot movieslot object that is going to be cancelled
     * @return true if successfully cancelled the seat, else false
     */
    public boolean cancelBooking(String TransactionID, MovieSlot movieSlot) {
        if (!this.TransactionID.contains(TransactionID)) {
            System.out.println("You do not have this transaction.");
            return false;
        }
        if (movieSlot.removeTicket(TransactionID)) {
            this.TransactionID.remove(TransactionID);
            TransactionID = "F" + TransactionID;
            this.CancelledTransactionID.add(TransactionID);
            UserManager.getInstance().Save();
            return true;
        } else {
            System.out.println("Transaction ID not found.");
            return false;
        }
    }
}
