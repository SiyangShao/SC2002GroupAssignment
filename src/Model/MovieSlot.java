package Model;

import java.time.LocalDateTime;

public class MovieSlot {
	private LocalDateTime datetime;
	private int MovieID;
	private int CinemaID;
	
	public MovieSlot() {
		
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public int getMovieID() {
		return MovieID;
	}

	public void setMovieID(int movieID) {
		MovieID = movieID;
	}

	public int getCinemaID() {
		return CinemaID;
	}

	public void setCinemaID(int cinemaID) {
		CinemaID = cinemaID;
	}
}
