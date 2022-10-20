package Controller;

import java.util.ArrayList;

import Model.Movie;
import Utils.Saveable;

public class MovieManager implements Saveable {
	private ArrayList<Movie> Movies;
	
	public MovieManager() {
		
	}
	
	public void addMovie(Movie m) {
		Movies.add(m);
	}
	public void deleteMovie(Movie m) {
		// change movie status to end
	}
	
	public void editMovie() {
		
	}

	@Override
	public void Save(String filepath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Load(String filepath) {
		// TODO Auto-generated method stub
		
	}
}
