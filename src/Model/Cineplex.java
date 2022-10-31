package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Controller.CineplexManager;

public class Cineplex implements Serializable {
	private String CineplexName;
	private String Location;
	private int CineplexID;
	private ArrayList<Cinema> Cinemas;
	
	public Cineplex(String name, String loc) {
		this.Cinemas = new ArrayList<>();
		this.CineplexName = name;
		this.Location = loc;
		this.setCineplexID(CineplexManager.getInstance().getSize() + 1);
	}

	public String getCineplexName() {
		return CineplexName;
	}

	public void setCineplexName(String cineplexName) {
		CineplexName = cineplexName;
	}

	public int getCineplexID() {
		return CineplexID;
	}

	public void setCineplexID(int cineplexID) {
		CineplexID = cineplexID;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}
	
	public Cinema addCinema(String name, int cinemaType) {
		Cinema cinema = new Cinema(name, cinemaType);
		this.Cinemas.add(cinema);
		CineplexManager.getInstance().Save();
		return cinema;
	}
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
	public ArrayList<Cinema> getCinemas() {
		return this.Cinemas;
	}
	public Cinema getOneCinema(int cinemaID) {
		Cinema cinema = null;
		for (int i =0; i < this.Cinemas.size(); i++) {
			if (this.Cinemas.get(i).getCinemaID() == cinemaID) cinema = this.Cinemas.get(i);
		}
		return cinema;
	}
}
