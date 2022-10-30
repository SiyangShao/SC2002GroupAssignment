package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Model.*;
import Utils.Config;
import Utils.Saveable;

public class MovieManager extends ManagerBase {
	private ArrayList<Movie> Movies;
	private static MovieManager instance;

	public MovieManager() {
		this.Movies = new ArrayList<>();

	}

	public int getSize() {
		return Movies.size();
	}

	public Movie addMovie(String title, int type, int status, int rating, String desc, int duration, String direc) {
		Movie m = new Movie(title, type, status, rating, desc, duration, direc);
		Movies.add(m);
		this.Save();
		return m;
	}

	public Movie removeMovie(int movieID) {
		Movie oneMovie = null;
		for (int i = 0; i < Movies.size(); i++) {
			if (Movies.get(i).getMovieID() == movieID)
				oneMovie = Movies.get(i);
		}
		if (oneMovie == null)
			return null;
		oneMovie.setStatus(MovieStatus.END_OF_SHOWING);
		return oneMovie;
	}

	@SuppressWarnings("unchecked")
	public Movie updateMovie(int movieID, int choice, Object newValue) {
		Movie oneMovie = null;
		for (int i = 0; i < Movies.size(); i++) {
			if (Movies.get(i).getMovieID() == movieID)
				oneMovie = Movies.get(i);
		}
		if (oneMovie == null)
			return null;

		switch (choice) {
		case 1:
			oneMovie.setTitle((String) newValue);
			break;
		case 2:
			oneMovie.setType((int) newValue);
			break;
		case 3:
			oneMovie.setStatus((int) newValue);
			break;
		case 4:
			oneMovie.setMovieRating((int) newValue);
			break;
		case 5:
			oneMovie.setDescription((String) newValue);
			break;
		case 6:
			oneMovie.setDurationMins((int) newValue);
			break;
		case 7:
			oneMovie.setDirector((String) newValue);
			break;
		case 8:
			oneMovie.setCast((ArrayList<String>) newValue);
			break;
		}
		this.Save();
		return oneMovie;
	}

	public ArrayList<Movie> getMovies() {
		return this.Movies;
	}
	public Movie getOneMovie(int movieID) {
		Movie movie = null;
		for (int i =0; i < this.Movies.size(); i++) {
			if (this.Movies.get(i).getMovieID() == movieID) movie = this.Movies.get(i);
		}
		return movie;
	}

	public MovieSlot addMovieSlot(LocalDateTime dt, int CinemaID, int movieID, int seatNumber, int basePrice) {
		// check if conflict time, if conflict return error, else add (WIP)
		MovieSlot ms = new MovieSlot(dt, movieID, CinemaID, seatNumber, basePrice);
		Movie movie = MovieManager.getInstance().getOneMovie(movieID);
		if (movie == null) return null;
		movie.AddSlot(ms);
		return ms;
	}
	public MovieSlot removeMovieSlot(int movieSlotID) {
		MovieSlot movieSlot = null;
		for (Movie movie : Movies) {
			for (MovieSlot ms : movie.getSlots()) {
				if (ms.getMovieSlotID() == movieSlotID) {
					movieSlot = ms;
					movie.removeSlot(ms);
				}
			}
		}
		return movieSlot;
	}

	@Override
	protected void SaveObjects(ObjectOutputStream out) throws IOException {
		out.writeObject(this.Movies);
	}

	@Override
	protected void LoadObjects(ObjectInputStream in) throws ClassNotFoundException, IOException {
		this.Movies = (ArrayList) in.readObject();
	}

	@Override
	public void Load(String filepath) {
		super.Load(filepath + Config.MovieManagerFileName);
	}

	public void Import(String filepath) {

	}

	public static MovieManager getInstance() {
		if (instance == null) {
			instance = new MovieManager();
		}
		return instance;
	}
}
