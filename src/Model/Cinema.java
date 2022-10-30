package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
;
import Controller.CineplexManager;
import Controller.MovieManager;

public class Cinema implements Serializable {
    private CinemaType type;
	private int CinemaID;
	private String CinemaName;
    
    public Cinema(String name, int ctype) {
    	setCinemaID();
    	this.CinemaName = name;
    	setType(ctype);
    }
	// one cineplex = many cinemas
    private void setCinemaID() {
		int count = 0;
		for (int i = 0; i < CineplexManager.getInstance().getCineplexes().size(); i++) {
			count += CineplexManager.getInstance().getCineplexes().get(i).getCinemas().size();

		}
		CinemaID = count+1;
	}
	// cinema can have multiple movies with multiple slots of each movie
	// removed Cinema.ArrayList<MovieID>, that implementation because have to remove Movie.MovieSlot and Cinema.MovieID instead of just MovieSlot
	// can be moved to moviemanager
    public ArrayList<ArrayList<MovieSlot>> CurrentMovieSlots() {
    	
    	ArrayList<Movie> Movies = MovieManager.getInstance().getMovies();
    	
        ArrayList<ArrayList<MovieSlot>> currentSlots = new ArrayList<>();
        for (Movie movie : Movies) {
			ArrayList<MovieSlot> slots = movie.getSlots(this.CinemaID);
			if (slots.size() > 0)
				currentSlots.add(slots);

        }
        return currentSlots;

    }

	public CinemaType getType() {
		return type;
	}
	
	public void setType(int type) {
    	switch(type) {
    	case 1: this.type = CinemaType.PLATINUM; break;
    	case 2: this.type = CinemaType.GOLD; break;
    	case 3: this.type = CinemaType.NORMAL; break;
    	default: break;
    	}
	}
	public void setType(CinemaType type) {
		this.type = type;
	}

	public int getCinemaID() {
		return CinemaID;
	}

	public String getCinemaName() {
		return CinemaName;
	}

	public void setCinemaName(String cinemaName) {
		CinemaName = cinemaName;
	}

}
