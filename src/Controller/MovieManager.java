package Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Model.*;
import Utils.Config;

/**
 Represents a MovieManager that manages all the Movies
 Extends from ManagerBase
 @author  Soh Wee Kiat
 @version 1.0
 @since   20 October 2022
 */

public class MovieManager extends ManagerBase {

    /**
     * The ArrayList which contains all the Movies
     */
    private ArrayList<Movie> Movies;

    /**
     * The only instance of itself, MovieManager
     */
    private static MovieManager instance;

    /**
     * Creates a new MovieManager
     */
    public MovieManager() {
        this.Movies = new ArrayList<>();

    }

    /**
     * Gets the size of the ArrayList of Movies
     * @return the size of the ArrayList of Movies
     */
    public int getSize() {
        return Movies.size();
    }

    /**
     * Adds Movie to the ArrayList of Movies
     * @param title The title of the Movie being added
     * @param type The MovieType of the Movie being added
     * @param status The showing status of the Movie being added
     * @param rating The rating of the Movie being added
     * @param desc The description of the Movie being added
     * @param duration The duration of the Movie being added
     * @param direc The director of the Movie being added
     * @return The Movie created
     */
    public Movie addMovie(String title, int type, int status, int rating, String desc, int duration, String direc) {
        Movie m = new Movie(title, type, status, rating, desc, duration, direc);
        Movies.add(m);
        this.Save();
        return m;
    }

    /**
     * Removes Movie from the ArrayList of Movies
     * @param movieID The movieID of the Movie to be removed
     * @return The removed Movie
     */
    public Movie removeMovie(int movieID) {
        Movie oneMovie = null;
        for (int i = 0; i < Movies.size(); i++) {
            if (Movies.get(i).getMovieID() == movieID)
                oneMovie = Movies.get(i);
        }
        if (oneMovie == null)
            return null;
        oneMovie.setStatus(MovieStatus.END_OF_SHOWING);
        this.Save();
        return oneMovie;
    }

    /**
     * Set price of the different CinemaType
     * @param type CinemaType which price is to be changed
     * @param price Price of the CinemaType to be changed to
     */
    public void setPrice(CinemaType type, double price) {
        for (int i = 0; i < Movies.size(); i++) {
            Movies.get(i).setPrice(type, price);
        }
        this.Save();
    }

    /**
     * Update Movie details using movieID
     * @param movieID movieID of the movie to be updated
     * @param choice User's choice to decide which details of the Movie to be updated
     * @param newValue The new value of the Movie detail to be updated to
     * @return The updated Movie
     */
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

    /**
     * Gets the ArrayList of Movies
     * @return the ArrayList of Movies
     */
    public ArrayList<Movie> getMovies() {
        return this.Movies;
    }

    /**
     * Gets one movie from the ArrayList of Movies using movieID
     * @param movieID The movieID of the Movie to be returned
     * @return The Movie that matches the movieID
     */
    public Movie getOneMovie(int movieID) {
        Movie movie = null;
        for (int i = 0; i < this.Movies.size(); i++) {
            if (this.Movies.get(i).getMovieID() == movieID) movie = this.Movies.get(i);
        }
        return movie;
    }

    /**
     * Adds MovieSlot to the Cinema
     * @param dt Date and time of the MovieSlot to be added
     * @param cinema Cinema of which the MovieSlot is to be added
     * @param movieID movieID of the Movie to be added
     * @param seatNumber Number of seats available for that MovieSlot
     * @param basePrice Base Price of the seats for that MovieSlot
     * @return MovieSlot that is being added
     */
    public MovieSlot addMovieSlot(LocalDateTime dt, Cinema cinema, int movieID, int seatNumber, int basePrice) {
        // check if conflict time, if conflict return error, else add (WIP)

        Model.DateType dateType = CalendarManager.getInstance().DateType(dt);
        CinemaType cinemaType = cinema.getType();
        MovieSlot ms = new MovieSlot(dt, movieID, cinema.getCinemaID(), seatNumber, basePrice, dateType, cinemaType);
        Movie movie = MovieManager.getInstance().getOneMovie(movieID);
        if (movie == null) return null;
        movie.AddSlot(ms);
        this.Save();
        return ms;
    }

    /**
     * Removes a particular MovieSlot based on the movieSlotID
     * @param movieSlotID The MovieSlot's ID to be removed
     * @return MovieSlot that is being removed
     */
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
        this.Save();
        return movieSlot;
    }

    /**
     * Implements the abstract method from MangerBase.
     * Saves the Movies created
     * @param out ObjectOutputStream for writing any java objects to file
     */
    @Override
    protected void SaveObjects(ObjectOutputStream out) throws IOException {
        out.writeObject(this.Movies);
    }

    /**
     * Implements the abstract method from MangerBase.
     * Load the Movies created
     * @param in ObjectInputStream for reading any java objects from file
     */
    @Override
    protected void LoadObjects(ObjectInputStream in) throws ClassNotFoundException, IOException {
        this.Movies = (ArrayList) in.readObject();
    }

    /**
     * Overriding the method from ManagerBase.
     * Load from the filepath
     * @param filepath String of the full file path to be written to
     */
    @Override
    public void Load(String filepath) {
        super.Load(filepath + Config.MovieManagerFileName);
    }

    /**
     * To ensure there is only one instance of itself, the MovieManager
     * @return the only MovieManager
     */
    public static MovieManager getInstance() {
        if (instance == null) {
            instance = new MovieManager();
        }
        return instance;
    }
}
