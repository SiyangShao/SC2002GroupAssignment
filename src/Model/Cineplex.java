package Model;

import java.util.ArrayList;

import Controller.CineplexManager;

public class Cineplex {
	private String CineplexName;
	private String Location;
	private int CineplexID;
	private ArrayList<Cinema> Cinemas;
	
	public Cineplex(String name, String loc) {
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
	
	
	
	
}
