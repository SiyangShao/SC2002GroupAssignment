package Model;

import java.io.Serializable;
import java.util.ArrayList;
import Controller.CineplexManager;

/**
 Represents a Cineplex
 @version 1.0
 @since   20 October 2022
 */
public class Cineplex implements Serializable {
    /**
     * Cineplex Name
     */
	private String CineplexName;
    /**
     * Cineplex Location
     */
	private String Location;
    /**
     * Cineplex ID
     */
	private int CineplexID;
    /**
     * List of Cinemas belonging to Cineplex
     */
	private ArrayList<Cinema> Cinemas;
	
    /**
     * Creates a new Cineplex with the given Name, Cineplex Location
     * @param name This Cineplex's Name
     * @param loc  This Cineplex's Location
     */
	public Cineplex(String name, String loc) {
		this.Cinemas = new ArrayList<>();
		this.CineplexName = name;
		this.Location = loc;
		this.setCineplexID(CineplexManager.getInstance().getSize() + 1);
	}
	
	
    /**
     * Get the Cineplex Name
     * @return Cineplex Name
     */
	public String getCineplexName() {
		return CineplexName;
	}

	/**
     * Set the Cineplex Name
     * @param cineplexName This Cineplex's Name
     */
	public void setCineplexName(String cineplexName) {
		CineplexName = cineplexName;
	}

	/**
     * Get the Cineplex ID
     * @return Cineplex ID
     */
	public int getCineplexID() {
		return CineplexID;
	}

	/**
     * Set the Cineplex ID
     * @param cineplexID This Cineplex's ID
     */
	public void setCineplexID(int cineplexID) {
		CineplexID = cineplexID;
	}

	/**
     * Get the Cineplex Location
     * @return Cineplex Location
     */
	public String getLocation() {
		return Location;
	}

	/**
     * Set the Cineplex Location
     * @param location This Cineplex's Location
     */
	public void setLocation(String location) {
		Location = location;
	}
	
	/**
     * Add new Cinema to Cineplex
     * @param name The Cinema's name
     * @param cinemaType The Cinema's Type
     * @return cinema
     */
	public Cinema addCinema(String name, int cinemaType) {
		Cinema cinema = new Cinema(name, cinemaType);
		this.Cinemas.add(cinema);
		CineplexManager.getInstance().Save();
		return cinema;
	}
	
	/**
     * Update a Cinema in Cineplex
     * @param cinemaID The Cinema's ID
     * @param choice Which property to update
     * @param value Data
     * @return cinema Created Cinema
     */
	public Cinema updateCinema(int cinemaID, int choice, Object value) {
		Cinema cinema = null;
		for (int i =0; i < this.Cinemas.size(); i++) {
			if (this.Cinemas.get(i).getCinemaID() == cinemaID)
				cinema = this.Cinemas.get(i);
		}
		if (cinema == null) return null;
		switch (choice) {
			case 1: cinema.setCinemaName((String) value); break;
			case 2: cinema.setType((int) value); break;
		}
		CineplexManager.getInstance().Save();
		return cinema;
	}
	
	/**
     * Remove Cinema from Cineplex
     * @param cinemaID This Cinema's ID
     * @return cinema Removed Cinema
     */
	public Cinema removeCinema(int cinemaID) {
		Cinema cinema = null;
		for (int i =0; i < this.Cinemas.size(); i++) {
			if (this.Cinemas.get(i).getCinemaID() == cinemaID)
				cinema = this.Cinemas.get(i);
				this.Cinemas.remove(i);
		}
		CineplexManager.getInstance().Save();
		return cinema;
	}
	
	
	/**
     * Get All Cinemas being to Cineplex
     * @return Cinemas List of Cinemas ArrayList<Cinema>
     */
	public ArrayList<Cinema> getCinemas() {
		return this.Cinemas;
	}
	
	/**
     * Get One Cinema using Cinema ID
     * @param cinemaID The Cinema's ID
     * @return cinema The Cinema to be returned
     */
	public Cinema getOneCinema(int cinemaID) {
		Cinema cinema = null;
		for (int i =0; i < this.Cinemas.size(); i++) {
			if (this.Cinemas.get(i).getCinemaID() == cinemaID) cinema = this.Cinemas.get(i);
		}
		return cinema;
	}
	
	/**
     * Get One Cinema's Type
     * @param cinemaID The Cinema's ID
     * @return cinemaType The Cinema's CinemaType
     */
	public CinemaType getCinemaType(int cinemaID){
		return this.getOneCinema(cinemaID).getType();
	}
}
