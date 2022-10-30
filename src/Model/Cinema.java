package Model;

import java.util.ArrayList;

import Controller.CinemaManager;
import Controller.MovieManager;

public class Cinema {
    private CinemaType type;
    private int CinemaID;
    private String CinemaName;
    private ArrayList<Integer> MovieID;
    
    public Cinema(String name, int ctype) {
    	this.CinemaID = CinemaManager.getInstance().getSize() + 1;
    	this.CinemaName = name;
    	setType(ctype);
    }
    
    public ArrayList<ArrayList<MovieSlot>> CurrentMovieSlots() {
    	
    	ArrayList<Movie> Movies = MovieManager.getInstance().getMovies();
    	
        ArrayList<ArrayList<MovieSlot>> currentSlots = new ArrayList<>();
        for (Movie movie : Movies) {
            if (this.MovieID.contains(movie.getMovieID())) {
                currentSlots.add(movie.getSlots(this.CinemaID));
            }
        }
        return currentSlots;

    }

	public CinemaType getType() {
		return type;
	}
	
	private void setType(int type) {
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
}
