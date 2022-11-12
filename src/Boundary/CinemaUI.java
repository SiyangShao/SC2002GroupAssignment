package Boundary;

import Controller.CineplexManager;
import Controller.MovieManager;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieSlot;
import Utils.Config;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * CinemaUI to display cinema related UI
 *
 * @version 1.0
 * @since 11 Nov 2022
 */
public class CinemaUI {
    /**
     * Reader for user input
     */
    private final Scanner sc;
    /**
     * Date formate
     */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM HH:mm");

    /**
     * Creates a new CinemaUI with the given Scanner
     *
     * @param sc This CinemaUI's Scanner.
     */
    public CinemaUI(Scanner sc) {
        this.sc = sc;

    }

    /**
     * Prints the main menu
     *
     * @param cineplex This CinemaUI's Cineplex.
     */
    public void handleCinemaCRUD(Cineplex cineplex) {
        int choice;
        do {
            System.out.println("What would you like to do?");
            System.out.println("1. Add Cinema");
            System.out.println("2. View Cinemas");
            System.out.println("3. Update Cinema");
            System.out.println("4. Remove Cinema");
            System.out.println("5. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    this.addCinema(cineplex);
                    break;
                case 2:
                    System.out.println("List of Cinemas:");
                    listCinema(cineplex);
                    break;
                case 3:
                    this.updateCinema(cineplex);
                    break;
                case 4:
                    this.removeCinema(cineplex);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid Choice.");
                    break;
            }
        } while (choice != 5);

    }

    /**
     * Handles the show times
     *
     * @param cineplex This CinemaUI's Cineplex.
     */
    public void handleShowTimes(Cineplex cineplex) {
        int choice;
        do {
            System.out.println("Update Cinema Showtimes");
            listCinema(cineplex);
            System.out.println("Which Cinema would you like to update?, (Enter 0 to exit)");
            int cinemaID = sc.nextInt();

            if (cinemaID == 0) return;
            Cinema cinema = cineplex.getOneCinema(cinemaID);
            if (cinema == null) {
                System.out.println("Invalid Cinema ID");
                return;
            }

            System.out.println("What would you like to do?");
            System.out.println("1. Add Showtime for " + cinema.getCinemaName());
            System.out.println("2. View Showtimes for " + cinema.getCinemaName());
            System.out.println("3. Remove Showtime for " + cinema.getCinemaName());
            System.out.println("4. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addShowTime(cinema);
                    break;
                case 2:
                    System.out.println("List of Showtimes for " + cinema.getCinemaName());
                    viewShowTimes(cinema, false);
                    break;
                case 3:
                    deleteShowTime(cinema);
                    return;
                default:
                    System.out.println("Invalid Choice.");
                    handleShowTimes(cineplex);
                    break;
            }
        } while (choice != 4);

    }

    /**
     * Add show time for cinema
     *
     * @param cinema The Cinema which need to add show time
     */
    private void addShowTime(Cinema cinema) {
        // do error checking (WIP)
        System.out.println("Add Showtime for Cinema " + cinema.getCinemaName());
        MovieUI movieUI = new MovieUI(sc);
        System.out.println("List of movies");
        movieUI.listMovies();
        System.out.println("Enter Movie to Show");
        int movieID = sc.nextInt();
        System.out.println("Enter Movie Showtime (24 hour format)");
        LocalDateTime dt = null;
        LocalDateTime today = LocalDateTime.now();
        do {
            dt = getInput_DateTime();
        } while (dt == null);
        if (dt.isBefore(today)) {
            System.out.println("You cannot create a timeslot before present time, Please choose another time");
            addShowTime(cinema);
            return;
        }
        if (!checkDateTimeValid(dt, cinema, movieID)) {
            System.out.println("This time has already been taken, Please choose another time");
            addShowTime(cinema);
            return;
        }
        MovieSlot movieslot = MovieManager.getInstance().addMovieSlot(dt, cinema, movieID, Config.Seats, Config.basePrice);
        if (movieslot == null) {
            System.out.println("Invalid Movie ID. Try Again");
            return;
        }
        System.out.println(movieslot.getMovieName() + " now showing on " + cinema.getCinemaName() + " at " + dt.format(formatter));
    }

    /**
     * View Show times for cinema
     *
     * @param cinema     The Cinema which need to view show time
     * @param byActualID If true, show actual movie ID, else show movie name
     */
    private void viewShowTimes(Cinema cinema, boolean byActualID) {
        ArrayList<ArrayList<MovieSlot>> msList = cinema.CurrentMovieSlots();
        if (msList == null || msList.size() == 0) {
            System.out.println("No Showtimes Found.");
            return;
        }
        for (int i = 0; i < msList.size(); i++) {
            for (int j = 0; j < msList.get(i).size(); j++) {
                MovieSlot slot = msList.get(i).get(j);
                if (byActualID) {
                    System.out.println(slot.getMovieSlotID() + ". Movie" + slot.getMovieName() + " showing at " + slot.getDatetime().format(formatter));
                } else {
                    System.out.println(i + j + 1 + ". Movie" + slot.getMovieName() + " showing at " + slot.getDatetime().format(formatter));
                }

            }
        }
    }

    /**
     * Delete show time for cinema
     *
     * @param cinema The Cinema which need to delete show time
     */
    private void deleteShowTime(Cinema cinema) {
        System.out.println("Delete Showtime for Cinema " + cinema.getCinemaName());
        viewShowTimes(cinema, true);
        System.out.println("Which Showtime would you like to remove?, (Enter 0 to exit)");
        int msID = sc.nextInt();
        if (msID == 0) return;
        MovieSlot ms = MovieManager.getInstance().removeMovieSlot(msID);
        if (ms == null) {
            System.out.println("Invalid Movie ID, Try Again");
            deleteShowTime(cinema);
            return;
        }
        System.out.println("Successfully deleted Showtime " + ms.getDatetime().format(formatter) + " for Movie " + ms.getMovieName());
    }

    /**
     * Add cinema to cineplex
     *
     * @param cineplex The Cineplex which need to add cinema
     */
    private void addCinema(Cineplex cineplex) {
        System.out.println("Add Cinema for " + cineplex.getCineplexName());
        Cinema cinema = cineplex.addCinema(getInput_Name(), getInput_Type());
        System.out.println("Successfully added Cinema " + cinema.getCinemaName() + " for Cineplex " + cineplex.getCineplexName());
    }

    /**
     * Update cinema for cineplex
     *
     * @param cineplex The Cineplex which need to update cinema
     */
    private void updateCinema(Cineplex cineplex) {
        System.out.println("Update Cinema");
        listCinema(cineplex);
        System.out.println("Which Cinema would you like to update?, (Enter 0 to exit)");
        int cinemaID = sc.nextInt();
        if (cinemaID == 0) return;
        System.out.println("What would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Type");
        System.out.println("3. Exit");
        int choice = sc.nextInt();
        Object value = null;
        switch (choice) {
            case 1:
                value = getInput_Name();
                break;
            case 2:
                value = getInput_Type();
                break;
            case 3:
                return;
            default:
                System.out.println("You did not select anything");
                updateCinema(cineplex);
                break;
        }
        Cinema cinema = cineplex.updateCinema(cinemaID, choice, value);
        if (cinema == null) {
            System.out.println("Invalid Cinema ID, Try again");
            updateCinema(cineplex);
        } else {
            System.out.println("Successfully updated Cinema " + cinema.getCinemaName());
        }
    }

    /**
     * Delete cinema for cineplex
     *
     * @param cineplex The Cineplex which need to delete cinema
     */
    private void removeCinema(Cineplex cineplex) {
        System.out.println("Remove Cinema");
        listCinema(cineplex);
        System.out.println("Which Cinema would you like to remove?, (Enter 0 to exit)");
        int cinemaID = sc.nextInt();
        if (cinemaID == 0) return;
        Cinema cinema = cineplex.removeCinema(cinemaID);
        if (cinema == null) {
            System.out.println("Invalid Cinema ID, Try again");
            removeCinema(cineplex);
            return;
        } else {
            System.out.println("Successfully removed Cinema " + cinema.getCinemaName());
        }
    }

    /**
     * List all cinema for cineplex
     *
     * @param cineplex The Cineplex which need to list cinema
     */
    private void listCinema(Cineplex cineplex) {
        ArrayList<Cinema> cinemas = cineplex.getCinemas();
        if (cinemas == null || cinemas.size() == 0) {
            System.out.println("No Cinemas Found.");
            return;
        }
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println(cinemas.get(i).getCinemaID() + ". " + cinemas.get(i).getCinemaName());
        }
    }

    /**
     * Get input for cinema name
     *
     * @return Cinema name
     */
    private String getInput_Name() {
        System.out.println("Enter Cinema Name");
        String name = sc.next();
        return name;
    }

    /**
     * Get input for cinema type
     *
     * @return Cinema type
     */
    private int getInput_Type() {
        System.out.println("Enter Cinema Type (1. PLATINUM, 2. GOLD, 3. NORMAL)");
        return sc.nextInt();
    }

    /**
     * Get input for date time
     *
     * @return Date time
     */
    private LocalDateTime getInput_DateTime() {
        System.out.println("Date-time in format: dd.MM HH:mm (e.g 20.11 13:00)");
        sc.nextLine();
        sc.findInLine("(\\d\\d)\\.(\\d\\d) (\\d\\d):(\\d\\d)");
        try {
            MatchResult mr = sc.match();
            int month = Integer.parseInt(mr.group(2));
            int day = Integer.parseInt(mr.group(1));
            int hour = Integer.parseInt(mr.group(3));
            int minute = Integer.parseInt(mr.group(4));
            LocalDateTime dt = LocalDateTime.of(2022, month, day, hour, minute);
            return dt;
        } catch (IllegalStateException e) {
            System.err.println("Invalid date-time format.");
            return null;
        }
    }

    /**
     * Check if the date time is valid
     *
     * @param newdt   The date time to check
     * @param cinema  The cinema to check
     * @param movieID The movie ID to check
     * @return True if the date time is valid, false otherwise
     */
    private boolean checkDateTimeValid(LocalDateTime newdt, Cinema cinema, int movieID) {
        ArrayList<Movie> movies = MovieManager.getInstance().getMovies();
        ArrayList<LocalDateTime> dts = new ArrayList<LocalDateTime>();
        for (Movie movie : movies) {
            ArrayList<MovieSlot> msList = movie.getSlots(cinema.getCinemaID());
            for (MovieSlot ms : msList) {
                dts.add(ms.getShowTime());
            }

        }
        LocalTime newStart = newdt.toLocalTime();
        LocalTime newEnd = newStart.plusMinutes(MovieManager.getInstance().getOneMovie(movieID).getDurationMins());

        // for each datetime check insert_dt not in (start + duration)
        for (LocalDateTime oneDT : dts) {
            // check same date
            if (oneDT.getYear() == newdt.getYear() && oneDT.getMonth() == newdt.getMonth() && oneDT.getDayOfMonth() == newdt.getDayOfMonth()) {
                LocalTime oneDTStart = oneDT.toLocalTime();
                LocalTime oneDTEnd = oneDTStart.plusMinutes(MovieManager.getInstance().getOneMovie(movieID).getDurationMins());
                //check start and end conflict
                if ((newStart.isAfter(oneDTStart) && newStart.isBefore(oneDTEnd)) || newStart.equals(oneDTStart)) {
                    return false;
                }
                if ((newEnd.isAfter(oneDTStart) && newEnd.isBefore(oneDTEnd)) || newEnd.equals(oneDTEnd)) {
                    return false;
                }

            }
        }
        return true;
    }
}
