package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Controller.MovieManager;

public class Movie implements Serializable {
    private int MovieID;
    private MovieRating MovieRating;
    private double ReviewRating;
    private MovieType Type;
    private String Title;
    private MovieStatus Status;
    private String Description;
    private String Director;
    private ArrayList<String> Cast;

    // =========Jeda===================
    private int TicketsSold = 0;
    private int NoOfReviewers = 0;
    private String ReviewComment;
//=========
    public void setRev(String reviewComment){
        ReviewComment = reviewComment;
    }

    public String getRevCom(){
        return ReviewComment;
    }
//==========  
    public void Tix(){
        this.TicketsSold += 1;
    }

    public int getTix(){
        return this.TicketsSold;
    }

    public int GetNoOfReviewers(){
        return this.NoOfReviewers;
    }

    private void NoOfReviwersIncre(){
        this.NoOfReviewers += 1;
    }

    private void AddReview(double reviewRating){
        this.ReviewRating += reviewRating;
    }

    public double GetAveRating(){
        return this.ReviewRating/this.NoOfReviewers;
    }

    public void AddRating(double rating){
        this.AddReview(rating);
        this.NoOfReviwersIncre();
    }

    // =========End Jeda===============
    
    private ArrayList<MovieSlot> Slots;

    private int DurationMins;

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
        MovieManager.getInstance().Save();
    }

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
        MovieManager.getInstance().Save();
    }

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int movieID) {
        MovieID = movieID;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public MovieStatus getStatus() {
        return Status;
    }

    public void setStatus(int status) {
    	switch(status) {
    	case 1: this.Status = MovieStatus.COMING_SOON; break;
    	case 2: this.Status = MovieStatus.PREVIEW; break;
    	case 3: this.Status = MovieStatus.NOW_SHOWING; break;
    	case 4: this.Status = MovieStatus.END_OF_SHOWING; break;
    	default: break;
    	}
    }

    public void setStatus(MovieStatus status) {
    	this.Status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public ArrayList<String> getCast() {
        return Cast;
    }

    public void setCast(ArrayList<String> cast) {
        Cast = cast;
    }

    public MovieType getType() {
        return Type;
    }

    public void setType(int type) {
    	switch(type) {
    	case 1: this.Type = MovieType.ThreeD; break;
    	case 2: this.Type = MovieType.BlockBuster; break;
    	case 3: this.Type = MovieType.Anime; break;
    	default: break;
    	}
    }
    public int getDurationMins() {
        return DurationMins;
    }

    public void setDurationMins(int durationMins) {
        DurationMins = durationMins;
    }
	public double getReviewRating() {
		return ReviewRating;
	}

	public void setReviewRating(double reviewRating) {
		ReviewRating = reviewRating;
	}

	public MovieRating getMovieRating() {
		return MovieRating;
	}
	public void setMovieRating(int rating) {
		switch(rating) {
    	case 1: this.MovieRating = MovieRating.G; break;
    	case 2: this.MovieRating = MovieRating.PG; break;
    	case 3: this.MovieRating = MovieRating.R18; break;
    	case 4: this.MovieRating = MovieRating.R21; break;
    	default: break;
    	}

	}
	public void setMovieRating(MovieRating movieRating) {
		MovieRating = movieRating;
	}

    // To print out the details of the movie
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
        // figure out how to not , at the end later
        System.out.println("Director       : " + Director);
        System.out.println("Synopsis       : " + Description);
        System.out.println("Type           : " + Type);
        System.out.println("Movie Rating   : " + MovieRating);
        if (Double.isNaN(ReviewRating/NoOfReviewers))
        {
            System.out.println("Review Comments: NA");
        }
        else
            System.out.println("Review Rating  : " + ReviewRating/NoOfReviewers);
        if (ReviewComment == null)
        {
            System.out.println("Review Comments: NA");
        }
        else
            System.out.println("Review Comments: " + ReviewComment);
        System.out.println("Runtime (Mins) : " + DurationMins);
        

    }

	public void AddSlot(MovieSlot newSlot) {
        Slots.add(newSlot);
    }

    public boolean querySlotExist(MovieSlot slot) {
        for (MovieSlot i : Slots) {
            if (i == slot) {
                return true;
            }
        }
        return false;
    }
    //used for setting MovieSlot ID
    public int getSlotCount() {
        return this.Slots.size();
    }
    public ArrayList<MovieSlot> getSlots() {
        return this.Slots;
    }
    public ArrayList<MovieSlot> getSlots(int CinemaID) {
        ArrayList<MovieSlot> list = new ArrayList<>();
        for (MovieSlot i : Slots) {
            if (i.getCinemaID() == CinemaID) {
                list.add(i);
            }
        }
        return list;
    }

    public void removeSlot(MovieSlot slot) {
        if (querySlotExist(slot))
            Slots.remove(slot);
    }

    public void editSlot(MovieSlot slotBefore, MovieSlot newSlot) {
        removeSlot(slotBefore);
        AddSlot(newSlot);
        MovieManager.getInstance().Save();
    }
    public MovieSlot getSlot(int slotID) {
        for (MovieSlot i : Slots) {
            if (i.getMovieSlotID() == slotID) {
                return i;
            }
        }
        return null;
    }
    public void setPrice(CinemaType type, double price) {
        for (MovieSlot i : Slots) {
                i.setPrice(type,price);
        }
    }
}
