package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    private ArrayList<MovieSlot> Slots;

    public Movie(String title, int type,  int status, int movieRating, String desc, String direc) {
    	this.Title = title;
        setType(type);
        setStatus(status);
    	setMovieRating(movieRating);
        this.Description = desc;
        this.Director = direc;
        this.MovieID = MovieManager.getInstance().getSize() + 1;
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
    }

}
