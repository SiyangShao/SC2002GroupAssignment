package Model;

import java.io.Serializable;

public class Review implements Serializable {
	private int MovieID;
	private String comment;
	private double rating;
	private int UserID;
	
	public Review(String comment, double rating, int userID)  {
		this.comment = comment;
		this.rating = rating;
		this.UserID = userID;
	}

	public int getMovieID() {
		return MovieID;
	}

	public void setMovieID(int movieID) {
		MovieID = movieID;
	}
	public int getUserID()  {
		return this.UserID;
	}
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
}
