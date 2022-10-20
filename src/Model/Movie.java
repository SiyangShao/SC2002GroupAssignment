package Model;

import java.util.ArrayList;

public class Movie {
	private int MovieID;
	private double Rating;
	private MovieType Type;
	private String Title;
	private String Status;
	private String Description;
	private String Director;
	private ArrayList<String> Cast;
	
	public Movie(MovieType type, String title, String status, String desc, String direc, ArrayList<String> c) {
		setType(type);
		Title = title;
		Status = status;
		Description = desc;
		Director = direc;
		Cast = c;
	}
	
	public int getMovieID() {
		return MovieID;
	}

	public void setMovieID(int movieID) {
		MovieID = movieID;
	}
	
	public double getRating() {
		return Rating;
	}

	public void setRating(double rating) {
		Rating = rating;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
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

	public void setType(MovieType type) {
		Type = type;
	}
	

}
