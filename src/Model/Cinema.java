package Model;

import java.io.Serializable;
import java.util.ArrayList;
import Controller.CineplexManager;
import Controller.MovieManager;

/**
 Represents a Cinema
 @version 1.0
 @since   20 October 2022
 */
public class Cinema implements Serializable {
    /**
     * Cinema Type of the Cinema
     */
    private CinemaType type;
    /**
     * System generated CinemaID for ease usage
     */
	private int CinemaID;
    /**
     * Name of the Cinema
     */
	private String CinemaName;
    
	
    /**
     * Creates a new Cinema with the given Name, Cinema Type
     * @param name This Cinema's Name
     * @param ctype  This Cinema's Cinema Type
     */
    public Cinema(String name, int ctype) {
    	setCinemaID();
    	this.CinemaName = name;
    	setType(ctype);
    }
    
    
    /**
     * System generated Cinema ID
     */
    private void setCinemaID() {
		int count = 0;
		for (int i = 0; i < CineplexManager.getInstance().getCineplexes().size(); i++) {
			count += CineplexManager.getInstance().getCineplexes().get(i).getCinemas().size();

		}
		CinemaID = count+1;
	}
	
    /**
     * Gets all Movie Slot for this Cinema
     * @return All MovieSlots of Each Movie in Cinema (ArrayList<ArrayList<MovieSlot>>)
     */
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

    /**
     * Return Cinema Type
     * @return All MovieSlots of Each Movie in Cinema (ArrayList<ArrayList<MovieSlot>>)
     */
	public CinemaType getType() {
		return type;
	}
	
	
    /**
     * Sets the Cinema Type
     * @param Set type of Cinema using int
     */
	public void setType(int type) {
    	switch(type) {
    	case 1: this.type = CinemaType.PLATINUM; break;
    	case 2: this.type = CinemaType.GOLD; break;
    	case 3: this.type = CinemaType.NORMAL; break;
    	default: break;
    	}
	}
    /**
     * Sets the Cinema Type
     * @param Set type of Cinema using CinemaType enum
     */
	public void setType(CinemaType type) {
		this.type = type;
	}

    /**
     * Returns the Cinema ID
     * @return Cinema ID
     */
	public int getCinemaID() {
		return CinemaID;
	}

    /**
     * Returns the Name of Cinema
     * @return Name of Cinema
     */
	public String getCinemaName() {
		return CinemaName;
	}
	
	
    /**
     * Sets the Name of Cinema
     * @param Name of Cinema
     */
	public void setCinemaName(String cinemaName) {
		CinemaName = cinemaName;
	}

}
