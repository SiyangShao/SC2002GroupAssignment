package Model;

import java.io.Serializable;
import java.util.ArrayList;
import Controller.MovieManager;
import Controller.UserManager;

/**
 Represents a Movie
 @version 1.0
 @since   20 October 2022
 */
public class Movie implements Serializable {
    /**
     * System generated MovieID for ease usage.
     */
    private int MovieID;

    /**
     * Rating of the Movie eg. R18, R21.
     */
    private MovieRating MovieRating;

    /**
     * Rating Score of Movie.
     */
    private double ReviewRating;

    /**
     * Type of Movie eg. BlockBuster, 3D.
     */
    private MovieType Type;

    /**
     * Title of Movie.
     */
    private String Title;

    /**
     * Status of Movie eg. Showing, Preview.
     */
    private MovieStatus Status;

    /**
     * Description of Movie.
     */
    private String Description;

    /**
     * Director of Movie.
     */
    private String Director;

    /**
     * List of the Movie actors/cast.
     */
    private ArrayList<String> Cast;

    /**
     * List of Movie reviews.  
     */
    private ArrayList<Review> Reviews;

    /**
     * Number of tickets sold.
     */
    private int TicketsSold = 0;
    
    /**
     * List of Movie Slots for the movie.
     */
    private ArrayList<MovieSlot> Slots;

    /**
     * Duration of the movie.
     */
    private int DurationMins;

    /**
     * Constructor for movie with given Title, Type, Movie Rating, Description, Duration and Director.
     * @param title The Movie's Title.
     * @param type  The Movie's Type.
     * @param status  The Movie Status.
     * @param movieRating  The Movie Rating.
     * @param desc  The Movie Descriprion.
     * @param duration  The Movie's Duration.
     * @param direc  The Movie's Director.
     */
    public Movie(String title, int type,  int status, int movieRating, String desc, int duration, String direc) {
    	this.Title = title;
        setType(type);
        setStatus(status);
    	setMovieRating(movieRating);
        this.Description = desc;
        this.DurationMins = duration;
        this.Director = direc;
        this.MovieID = MovieManager.getInstance().getSize() + 1;
        this.Slots = new ArrayList<>();
        this.Cast = new ArrayList<>();
        this.Reviews = new ArrayList<>();
        MovieManager.getInstance().Save();
    }

    /**
     * Constructor for movie with given Title, Type, Movie Rating, Description, Duration and Director.
     * @param title The Movie's Title.
     * @param type  The Movie's Type.
     * @param status  The Movie Status.
     * @param movieRating  The Movie Rating.
     * @param desc  The Movie Descriprion.
     * @param duration  The Movie's Duration.
     * @param direc  The Movie's Director.
     * @param c List of Movie's Casts.
     */
    public Movie(String title, int type,  int status, int movieRating, String desc, String direc, ArrayList<String> c) {
        this.Title = title;
        this.Slots = new ArrayList<>();
        setType(type);
        setStatus(status);
    	setMovieRating(movieRating);
        this.Description = desc;
        this.Director = direc;
        this.Cast = c;
        this.MovieID = MovieManager.getInstance().getSize() + 1;
        this.Slots = new ArrayList<>();
        this.Cast = new ArrayList<>();
        this.Reviews = new ArrayList<>();
        MovieManager.getInstance().Save();
    }

    
    /**
     * Add the number of tickets bought.
    */
    public void Tix(){
        this.TicketsSold += 1;
    }

    /**
     * Get the number of tickets sold.
     * @return number of tickets sold.
     */
    public int getTix(){
        return this.TicketsSold;
    }

    /**
     * Get the MovieID of the Movie.
     * @return the MovieID of Movie.
     */
    public int getMovieID() {
        return MovieID;
    }

    /**
     * Changes movieID of the Movie.
     * @param movieID This is the ID of the Movie.
     */
    public void setMovieID(int movieID) {
        MovieID = movieID;
    }

    /**
     * Get the Title of the Movie.
     * @return Title of the Movie.
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Changes the of the Movie.
     * @param title This is the title of the Movie.
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     * Get the Status of the Movie.
     * @return The Status of the Movie.
     */
    public MovieStatus getStatus() {
        return Status;
    }

    /**
     * Changes the Status of the Movie through user choice.
     * @param status This is the Status of the Movie.
     */
    public void setStatus(int status) {
    	switch(status) {
    	case 1: this.Status = MovieStatus.COMING_SOON; break;
    	case 2: this.Status = MovieStatus.PREVIEW; break;
    	case 3: this.Status = MovieStatus.NOW_SHOWING; break;
    	case 4: this.Status = MovieStatus.END_OF_SHOWING; break;
    	default: break;
    	}
    }

    /**
     * Changes the Status of the Movie.
     * @param status
     */
    public void setStatus(MovieStatus status) {
    	this.Status = status;
    }

    /**
     * Get the description of the Movie.
     * @return Description of the Movie.
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Changes the description of the Movie.
     * @param description This is the Movie desctiption.
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * Gets the director of the Movie.
     * @return The Movie Director
     */
    public String getDirector() {
        return Director;
    }

    /**
     * Changes the Movie Director.
     * @param director This is the Movie Director.
     */
    public void setDirector(String director) {
        Director = director;
    }

    /**
     * Gets the List of Cast of the Movie.
     * @return List of Cast of the Movie
     */
    public ArrayList<String> getCast() {
        return Cast;
    }

    /**
     * Changes the List of Cast of the Movie.
     * @param cast This is the List of Cast.
     */
    public void setCast(ArrayList<String> cast) {
        Cast = cast;
    }

    /**
     * Get the Movie Type.
     * @return Type of Movie.
     */
    public MovieType getType() {
        return Type;
    }

    /**
     * Changes the Movie Type.
     * @param type This is the Movie Type.
     */
    public void setType(int type) {
    	switch(type) {
    	case 1: this.Type = MovieType.ThreeD; break;
    	case 2: this.Type = MovieType.BlockBuster; break;
    	case 3: this.Type = MovieType.Anime; break;
    	default: break;
    	}
    }

    /**
     * Get the duration of the Movie.
     * @return Duration of the Movie.
     */
    public int getDurationMins() {
        return DurationMins;
    }
    
    /**
     * Changes the duration of the Movie.
     * @param durationMins This the Movie duration.
     */
    public void setDurationMins(int durationMins) {
        DurationMins = durationMins;
    }

    /**
     * Get the review rating of Movie
     * @return The review rating of the movie.
     */
	public double getReviewRating() {
		return ReviewRating;
	}
	
    /**
     * Adds the reviews to the Movie
     * @param comment This is the Reviews.
     * @param rating This is the Review Rating.
     * @param userID This is the UserID of the user.
     */
	public void addMovieReview(String comment, double rating, int userID) {
		Review review = new Review(comment,rating, userID);
		this.Reviews.add(review);
	}
	
    /**
     * Changes the review Rating of the Movie.
     * @param reviewRating This is the Review Rating.
     */
	public void setReviewRating(double reviewRating) {
		ReviewRating = reviewRating;
	}

    /**
     * Gets the Movie Rating of the Movie.
     * @return Movie Rating.
     */
	public MovieRating getMovieRating() {
		return MovieRating;
	}

    /**
     * Changes the rating of the Movie though selection.
     * @param rating Rating of the Movie.
     */
	public void setMovieRating(int rating) {
		switch(rating) {
    	case 1: this.MovieRating = MovieRating.G; break;
    	case 2: this.MovieRating = MovieRating.PG; break;
    	case 3: this.MovieRating = MovieRating.R18; break;
    	case 4: this.MovieRating = MovieRating.R21; break;
    	default: break;
    	}

	}

    /**
     * Changes the rating of the Movie.
     * @param movieRating Rating of the Movie.
     */
	public void setMovieRating(MovieRating movieRating) {
		MovieRating = movieRating;
	}

    /**
     * Print out the details of the Movie.
     */
    public void printDetails() {
        System.out.println(Title);
        System.out.println("================================");
        System.out.println("Showing Status : " + Status);
        System.out.println("Movie Type     : " + Type);

        System.out.printf("Cast           : ");
        for(int i = 0 ; i < Cast.size(); ++i){
            System.out.printf(Cast.get(i) + (i == Cast.size() - 1 ? "" : ","));
        }
        System.out.println();
        System.out.println("Director       : " + Director);
        System.out.println("Synopsis       : " + Description);
        System.out.println("Type           : " + Type);
        System.out.println("Movie Rating   : " + MovieRating);
        
       
        if (getAvgRating() == 0) System.out.println("Review Rating	: NA");
        else System.out.println("Review Rating: " + getAvgRating());
        
        System.out.println("Review Comments: ");
        for(int i = 0 ; i < Reviews.size(); i++){
        	if (Reviews.size() == 0) {
            	System.out.printf(" NA");
            }
        	System.out.println("   Name: " + UserManager.getInstance().GetUserById(Reviews.get(i).getUserID()).getName() + ", Comment: " +  Reviews.get(i).getComment());
        }
        
        	
        System.out.println("Runtime (Mins) : " + DurationMins);
        

    }

    /**
     * Add movie slot for Movie.
     * @param newSlot movie slot for the Movie.
     */
	public void AddSlot(MovieSlot newSlot) {
        Slots.add(newSlot);
    }

    /**
     * Check if slot is in use.
     * @param slot This is the movie slot.
     * @return Whether slot is in use.
     */
    public boolean querySlotExist(MovieSlot slot) {
        for (MovieSlot i : Slots) {
            if (i == slot) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get the number of slots for the Movie.
     * @return The number of slots.
     */
    public int getSlotCount() {
        return this.Slots.size();
    }

    /**
     * Gets the list of movie slots for the Movie.
     * @return List of movie slots for the Movie.
     */
    public ArrayList<MovieSlot> getSlots() {
        return this.Slots;
    }

    /**
     * Gets the list of movie slots for the Cinema.
     * @param CinemaID This is the Cinema ID.
     * @return List of slots for the Cinema.
     */
    public ArrayList<MovieSlot> getSlots(int CinemaID) {
        ArrayList<MovieSlot> list = new ArrayList<>();
        for (MovieSlot i : Slots) {
            if (i.getCinemaID() == CinemaID) {
                list.add(i);
            }
        }
        return list;
    }
    /**
     * Removes the movie slot of the Movie.
     * @param slot This is the movie slot.
     */
    public void removeSlot(MovieSlot slot) {
        if (querySlotExist(slot))
            Slots.remove(slot);
    }
    /**
     * Edit the movie slot of the movie.
     * @param slotBefore This is the movie slot before editing.
     * @param newSlot This is the movie slot after editing.
     */
    public void editSlot(MovieSlot slotBefore, MovieSlot newSlot) {
        removeSlot(slotBefore);
        AddSlot(newSlot);
        MovieManager.getInstance().Save();
    }

    /**
     * Gets one movie slot.
     * @param slotID This is the slot ID.
     * @return The movie slot.
     */
    public MovieSlot getSlot(int slotID) {
        for (MovieSlot i : Slots) {
            if (i.getMovieSlotID() == slotID) {
                return i;
            }
        }
        return null;
    }

    /**
     * Changes the Price of the Ticket.
     * @param type This is the Cinema Type.
     * @param price This is the price of the ticket.
     */
    public void setPrice(CinemaType type, double price) {
        for (MovieSlot i : Slots) {
                i.setPrice(type,price);
        }
    }
    
    /**
     * Gets the averge review rating of the Movie.
     * @return The average review rating of the Movie.
     */
    public double getAvgRating() {
    	double totalrating = 0;
        for (Review review : Reviews) {
        	totalrating += review.getRating();
        }
        if (Reviews.size() > 0) totalrating = totalrating / Reviews.size();
        return totalrating;
    }
}
