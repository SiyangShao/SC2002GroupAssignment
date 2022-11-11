package Model;

import java.io.Serializable;

/**
Represents one Review for one Movie in Movie Booking application
A Movie can have multiple Reviews
@version 1.0
@since   20 October 2022
*/
public class Review implements Serializable {
	
	/**
     * The User's comment for the movie
     */
	private String comment;
	/**
     * The User's rating for the movie
     */
	private double rating;
	/**
     * The User's ID for this review
     */
	private int UserID;
	
	
	/**
     * Creates a Review for one Movie
     * @param comment The User's comment
     * @param rating The User's rating
     * @param userID The User's userID
     */
	public Review(String comment, double rating, int userID)  {
		this.comment = comment;
		this.rating = rating;
		this.UserID = userID;
	}
	
	/**
     * Returns the UserID
     * @return UserID The User's UserID
     */
	public int getUserID()  {
		return this.UserID;
	}
	
	/**
     * Returns the Review Comment
     * @return comment The User's Comment
     */
	public String getComment() {
		return comment;
	}

	/**
     * Sets the Review Comment
     * @param comment The Review's Comment
     */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
     * Returns the Review Rating
     * @return rating The User's Rating
     */
	public double getRating() {
		return rating;
	}

	/**
     * Set the Review Rating
     * @param rating The Review's Rating
     */
	public void setRating(double rating) {
		this.rating = rating;
	}
}
