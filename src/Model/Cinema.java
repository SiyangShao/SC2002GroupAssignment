package Model;

import java.util.ArrayList;

public class Cinema {
    private CinemaType type;
    private int CinemaID;
    private ArrayList<Integer> MovieID;

    public ArrayList<ArrayList<MovieSlot>> CurrentMovieSlots(ArrayList<Movie> Movies) {
        ArrayList<ArrayList<MovieSlot>> currentSlots = new ArrayList<>();
        for (Movie movie : Movies) {
            if (this.MovieID.contains(movie.getMovieID())) {
                currentSlots.add(movie.getSlots(this.CinemaID));
            }
        }
        return currentSlots;

    }
}
