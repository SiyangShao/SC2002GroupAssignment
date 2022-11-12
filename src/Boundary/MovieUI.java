package Boundary;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.CalendarManager;
import Controller.MovieManager;

import java.time.LocalDateTime;

import Model.*;

import java.util.regex.MatchResult;

import Model.Movie;

/**
 * Represents a CalendarUI
 *
 * @version 1.0
 * @since 11 November 2022
 */
public class MovieUI {
    /**
     * Scanner to read user input
     */
    private final Scanner sc;

    /**
     * Creates a new MovieUI with the given Scanner
     *
     * @param sc Scanner to read user input
     */
    public MovieUI(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Display the movie menu
     */
    public void HandleMovieUI() {
        System.out.println("What would you like to do?");
        System.out.println("1. Add New Movie");
        System.out.println("2. Edit Movie");
        System.out.println("3. Delete Movie");
        System.out.println("4. View Movie");
        System.out.println("5. Exit");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                addMovie();
                break;
            case 2:
                updateMovie();
                break;
            case 3:
                removeMovie();
                break;
            case 4:
                System.out.println("List of Movies");
                listMovies();
                HandleMovieUI();
                break;
            default:
                break;
        }
    }

    /**
     * Add Movie
     */
    private void addMovie() {
        // String title,MovieType type, MovieStatus status, String desc, String direc
        System.out.println("Create New Movie");
        Movie m = MovieManager.getInstance().addMovie(getInput_Title(), getInput_Type(), getInput_Status(), getInput_MovieRating(), 
                getInput_Desc(), getInput_Duration(), getInput_Direc());
        System.out.println("Successfully added new Movie: " + m.getTitle());
    }

    /**
     * Update Movie Menu
     */

    private void updateMovie() {
        Object value = null;
        System.out.println("Update movie");
        listMovies();
        System.out.println("Enter Movie ID to update. (Enter 0 to exit)");
        int mID = sc.nextInt();
        if (mID == 0)
            return;
        System.out.println("What would you like to update?");
        System.out.println("1. Title");
        System.out.println("2. Type");
        System.out.println("3. Status");
        System.out.println("4. Movie Rating");
        System.out.println("5. Description");
        System.out.println("6. Duration");
        System.out.println("7. Director");
        System.out.println("8. List of cast");
        System.out.println("9. Exit");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                value = getInput_Title();
                break;
            case 2:
                value = getInput_Type();
                break;
            case 3:
                value = getInput_Status();
                break;
            case 4:
                value = getInput_MovieRating();
                break;
            case 5:
                value = getInput_Desc();
                break;
            case 6:
                value = getInput_Duration();
                break;
            case 7:
                value = getInput_Direc();
                break;
            case 8:
                value = getInput_Cast();
                break;
            case 9:
                return;
            default:
                System.out.println("You did not select anything");
                updateMovie();
                break;
        }
        Movie m = MovieManager.getInstance().updateMovie(mID, choice, value);
        if (m == null) {
            System.out.println("Invalid Movie ID, Try again");
            updateMovie();
        } else {
            System.out.println("Successfully updated Movie " + m.getTitle());
        }
    }

    /**
     * System Setting Menu
     */
    public void HandleSystemSettings() {
        System.out.println("Please enter configuration settings");
        System.out.println("1. Change Holiday Settings");
        System.out.println("2. Change Movie Ticket Price for different Cinema Class");
        System.out.println("3. Exit");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                HandleHolidaySettings();
                break;
            case 2:
                HandleCinemaClassSettings();
                break;
            case 3:
                return;
            default:
                System.out.println("You did not select anything");
                HandleSystemSettings();
                break;
        }
    }

    /**
     * Holiday Settings Menu
     */
    private void HandleHolidaySettings() {
        System.out.println("Please enter Holiday Settings");
        System.out.println("1. Add Holiday");
        System.out.println("2. Remove Holiday");
        System.out.println("3. Exit");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter Holiday Showtime");
                for (LocalDateTime date : getInput_DateTime()) {
                    CalendarManager.getInstance().AddHoliday(date);
                }
                CalendarManager.getInstance().Save();
                break;
            case 2:
                System.out.println("Enter Holiday Showtime");
                for (LocalDateTime date : getInput_DateTime()) {
                    if (CalendarManager.getInstance().isHoliday(date)) {
                        CalendarManager.getInstance().RemoveHoliday(date);
                    } else {
                        System.out.println("Date is not a holiday");
                        break;
                    }
                }
                CalendarManager.getInstance().Save();
                break;
            case 3:
                return;
        }
    }

    /**
     * Get input all time
     *
     * @return ArrayList of LocalDateTime which includes all the holiday time
     */
    private ArrayList<LocalDateTime> getInput_DateTime() {
        System.out.println("Date-time in format: dd.MM");
        sc.nextLine();
        sc.findInLine("(\\d\\d)\\.(\\d\\d)");
        try {
            MatchResult mr = sc.match();
            int month = Integer.parseInt(mr.group(2));
            int day = Integer.parseInt(mr.group(1));
            ArrayList<LocalDateTime> dt = new ArrayList<LocalDateTime>();
            for (int i = 0; i < 24; ++i) {
                for (int j = 0; j < 60; ++j) {
                    dt.add(LocalDateTime.of(2022, month, day, i, j));
                }
            }
            return dt;
        } catch (IllegalStateException e) {
            System.err.println("Invalid date-time format.");
            return null;
        }
    }

    /**
     * Cinema Class Settings Menu
     */
    public void HandleCinemaClassSettings() {
        System.out.println("Please input new Price for Cinema Class");
        System.out.println("1. Platinum");
        System.out.println("2. Gold");
        System.out.println("3. Normal");
        System.out.println("4. Exit");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter new price for Platinum");
                double price = sc.nextDouble();
                MovieManager.getInstance().setPrice(CinemaType.PLATINUM, price);
                MovieManager.getInstance().Save();
                break;
            case 2:
                System.out.println("Enter new price for Gold");
                price = sc.nextDouble();
                MovieManager.getInstance().setPrice(CinemaType.GOLD, price);
                MovieManager.getInstance().Save();
                break;
            case 3:
                System.out.println("Enter new price for Normal");
                price = sc.nextDouble();
                MovieManager.getInstance().setPrice(CinemaType.NORMAL, price);
                MovieManager.getInstance().Save();
                break;
            case 4:
                return;
            default:
                System.out.println("You did not select anything");
                HandleCinemaClassSettings();
                break;
        }
    }

    /**
     * Remove Movie Menu
     */
    private void removeMovie() {
        System.out.println("Remove Movie");
        listMovies();
        System.out.println("Enter Movie ID to delete. (Enter 0 to exit)");
        int mID = sc.nextInt();
        if (mID == 0)
            return;
        Movie m = MovieManager.getInstance().removeMovie(mID);
        if (m == null) {
            System.out.println("Invalid Movie ID, Try again");
            removeMovie();
        } else {
            System.out.println("Successfully removed Movie " + m.getTitle());
        }
    }

    /**
     * List all movies
     */
    public void listMovies() {
        ArrayList<Movie> movies = MovieManager.getInstance().getMovies();
        if (movies == null) {
            System.out.println("No Movies Found.");
            return;
        }
        for (int i = 0; i < movies.size(); i++) {
            Movie oneMovie = movies.get(i);
            System.out.println(oneMovie.getMovieID() + ". " + oneMovie.getTitle());
        }
    }

    /**
     * Get input movie type
     *
     * @return MovieType in int
     */
    private int getInput_Type() {
        System.out.println("Enter Movie Type (1. 3D, 2. BlockBuster, 3. Anime)");
        return sc.nextInt();
    }

    /**
     * Get input movie title
     *
     * @return Movie title in String
     */

    private String getInput_Title() {
        System.out.println("Enter Movie Title");
        sc.nextLine();        // To remove the carriage return character
        return sc.nextLine();
    }

    /**
     * Get input movie status
     *
     * @return MovieStatus in int
     */
    private int getInput_Status() {
        System.out.println("Enter Movie Status (1. COMING SOON, 2. PREVIEW, 3. NOW SHOWING, 4. END OF SHOWING)");
        return sc.nextInt();
    }

    /**
     * Get input movie rating
     *
     * @return MovieRating in int
     */
    private int getInput_MovieRating() {
        System.out.println("Enter Movie Rating (1. G, 2. PG, 3. R18, 4. R21)");
        return sc.nextInt();
    }

    /**
     * Get input movie duration
     *
     * @return Movie duration in int
     */

    private int getInput_Duration() {
        System.out.println("Enter Movie Duration in Minutes");
        return sc.nextInt();
    }

    /**
     * Get input movie description
     *
     * @return Movie description in String
     */

    private String getInput_Desc() {
        System.out.println("Enter Movie Description");
        sc.nextLine();        // To remove the carriage return character
        return sc.nextLine();
    }

    /**
     * Get input movie cast
     *
     * @return Movie cast in String
     */

    private ArrayList<String> getInput_Cast() {
        ArrayList<String> cast = new ArrayList<String>();
        System.out.println("Enter How Many Cast Members:");
        int amount = sc.nextInt();
        sc.nextLine();        // To remove the carriage return character
        for (int i = 0; i < amount; i++) {
            System.out.println("Enter Cast Name:");
            cast.add(sc.nextLine());
        }
        return cast;
    }

    /**
     * Get input movie director
     *
     * @return Movie director in String
     */
    private String getInput_Direc() {
        System.out.println("Enter Movie Director");
        sc.nextLine();        // To remove the carriage return character
        return sc.nextLine();
    }

}
