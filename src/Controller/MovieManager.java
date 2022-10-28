package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import Model.Movie;
import Model.MovieStatus;
import Model.MovieType;
import Utils.Config;
import Utils.Saveable;

public class MovieManager implements Saveable {
	private ArrayList<Movie> Movies;
	private static MovieManager instance;
	public MovieManager() {
		this.Movies = new ArrayList<>();

	}
	
	public int getSize() {
		return Movies.size();
	}
	
	public void addMovie(String title, int type,  int status, int rating, String desc, String direc) {
		Movie m = new Movie( title, type, status, rating, desc, direc);
		Movies.add(m);
		String CurPath = Paths.get("").toAbsolutePath().toString() + "/";
        Save(CurPath);
	}

	public Movie removeMovie(int movieID) {
		Movie oneMovie = null;
		for (int i =0; i < Movies.size(); i++) {
			if (Movies.get(i).getMovieID() == movieID) oneMovie = Movies.get(i); 
		}
		if (oneMovie == null) return null;
		oneMovie.setStatus(MovieStatus.END_OF_SHOWING);
		return oneMovie;
	}
	
	@SuppressWarnings("unchecked")
	public Movie updateMovie(int movieID, int choice, Object newValue) {
		Movie oneMovie = null;
		for (int i =0; i < Movies.size(); i++) {
			if (Movies.get(i).getMovieID() == movieID) oneMovie = Movies.get(i); 
		}
		if (oneMovie == null) return null;
		
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
	    		oneMovie.setDirector((String) newValue);
	        	break;
	    	case 7:
	    		oneMovie.setCast((ArrayList<String>) newValue);
	    		break;
	        
		}
		String CurPath = Paths.get("").toAbsolutePath().toString() + "/";
        Save(CurPath);
		return oneMovie;
	}
	
	public ArrayList<Movie> getMovies() {
		return this.Movies;
	}

	@Override
	public void Save(String filepath) {
		// TODO Auto-generated method stub
        try {
            FileOutputStream fos = new FileOutputStream(filepath + Config.MovieManagerFileName);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(this.Movies);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

	@Override
	public void Load(String filepath) {
		// TODO Auto-generated method stub
		 try {
	            FileInputStream fis = new FileInputStream(filepath + Config.MovieManagerFileName);
	            ObjectInputStream in = new ObjectInputStream(fis);
	            this.Movies = (ArrayList) in.readObject();
	            in.close();
	        }catch (FileNotFoundException e){
	            e.printStackTrace();
	            this.Save(filepath);
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	            this.Save(filepath);
	        } catch (ClassNotFoundException e) {
	            this.Save(filepath);
	        }
	}
	
	public void Import(String filepath) {
		
	}

	public static MovieManager getInstance() {
		if (instance == null) {
			instance = new MovieManager();
		}
		return instance;
	}

	public static void setInstance(MovieManager instance) {
		MovieManager.instance = instance;
	}
}
