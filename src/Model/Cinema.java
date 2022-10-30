package Model;

import java.util.ArrayList;
;
import Controller.CineplexManager;
import Controller.MovieManager;

public class Cinema {
    private CinemaType type;
	private int CinemaID;
	private String CinemaName;
    private ArrayList<Integer> MovieID;
    
    public Cinema(String name, int ctype) {
    	this.CinemaID = getLatestCinemaID()+1;
    	this.CinemaName = name;
    	setType(ctype);
    }
	// one cineplex = many cinemas
	// don't duplicate cinema ID so loop entire cineplex + cinemas to get latest ID
	// got better solution ?
    private int getLatestCinemaID() {
		ArrayList<Cineplex> cineplexes = CineplexManager.getInstance().getCineplexes();
		int count = 0;
		for (int i = 0; i < cineplexes.size(); i++) {
			for (int j = 0; j < cineplexes.get(i).getCinemas().size(); j++) {
				count++;
			}
		}
		return count;
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

	public void setCinemaID(int cinemaID) {
		CinemaID = cinemaID;
	}

	public String getCinemaName() {
		return CinemaName;
	}

	public void setCinemaName(String cinemaName) {
		CinemaName = cinemaName;
	}

}
