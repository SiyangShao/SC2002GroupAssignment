package Boundary;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MovieManager;
import Model.Movie;
import Model.MovieStatus;

public class MovieUI {
	private static Scanner sc = new Scanner(System.in);

	public static void MovieEditor() {
		System.out.println("What would you like to do?");
		System.out.println("1. Add New Movie");
		System.out.println("2. Edit Movie");
		System.out.println("3. Delete Movie");
		int choice = sc.nextInt();
		switch (choice) {
			case 1: addMovie(); break;
			case 2: updateMovie(); break;
			case 3: removeMovie(); break;
			case 4: listMovies(); break;
			default: break;
		}
	}
	
	private static void addMovie() {
	   	// String title,MovieType type, MovieStatus status, String desc, String direc
    	System.out.println("Create New Movie"); 
    	String title = getInput_Title();
    	MovieManager.getInstance().addMovie(title, getInput_Type(), getInput_MovieRating(), getInput_Status(), getInput_Desc(), getInput_Direc());
    	System.out.println("Successfully added new Movie: " + title);
	}
	
	
	
	private static void updateMovie() {
		Object value = null;
    	System.out.println("Update movie");
    	listMovies();
    	System.out.println("Enter Movie ID to update. (Enter 0 to exit)");
    	int mID = sc.nextInt();
    	if (mID == 0) return;
    	System.out.println("What would you like to update?");
        System.out.println("1. Title");
        System.out.println("2. Type");
        System.out.println("3. Status");
        System.out.println("4. Movie Rating");
        System.out.println("5. Description");   
        System.out.println("6. Director");
        System.out.println("7. List of cast");
        System.out.println("8. Exit");
    	int choice = sc.nextInt();
    	switch (choice) {
	    	case 1: 
	    		value = getInput_Title();
	    		break;
	    	case 2: 
	        	value =  getInput_Type();
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
	    		value = getInput_Direc();
	        	break;
	    	case 7:
	    		value = getInput_Cast();
	    		break;
	    	case 8: 
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
    	}
    	else {
        	System.out.println("Successfully updated Movie " + m.getTitle());
    	}
	}
	
	private static void removeMovie() {
		System.out.println("Remove Movie");
		listMovies();
		System.out.println("Enter Movie ID to delete. (Enter 0 to exit)");
		int mID = sc.nextInt();
    	if (mID == 0) return;
    	Movie m = MovieManager.getInstance().removeMovie(mID);
    	if (m == null) {
    		System.out.println("Invalid Movie ID, Try again");
    		removeMovie();
    	}
    	else {
        	System.out.println("Successfully removed Movie " + m.getTitle());
    	}
	}
	
	private static void listMovies() {
		ArrayList<Movie> movies = MovieManager.getInstance().getMovies();
		for (int i = 0; i < movies.size(); i++) {
			Movie oneMovie = movies.get(i);
			if (oneMovie.getStatus() != MovieStatus.END_OF_SHOWING)
				System.out.println(oneMovie.getMovieID() + ". " + oneMovie.getTitle());
		}
	}

	private static int getInput_Type() {
		System.out.println("Enter Movie Type (1. 3D, 2. BlockBuster, 3. Anime)");
		return sc.nextInt();
	}
	private static String getInput_Title() {
		System.out.println("Enter Movie Title");
    	return sc.next();
	}
	private static int getInput_Status() {
		System.out.println("Enter Movie Status (1. COMING SOON, 2. PREVIEW, 3. NOW SHOWING, 4. END OF SHOWING)");
    	return sc.nextInt();
	}
	private static int getInput_MovieRating() {
		System.out.println("Enter Movie Rating (1. G, 2. PG, 3. R18, 4. R21)");
    	return sc.nextInt();
	}
	private static String getInput_Desc() {
		System.out.println("String Movie Description");
    	return sc.next();
	}
	private static ArrayList<String> getInput_Cast() {
		ArrayList<String> cast = new ArrayList<String>();
		System.out.println("Enter How Many Cast Members:");
		int amount = sc.nextInt();
		for (int i =0; i < amount;i++) {
			System.out.println("Enter Cast Name:");
			cast.add(sc.next());
		}
		return cast;
	}
	private static String getInput_Direc() {
		System.out.println("Enter Movie Director");
    	return sc.next();
	}
	
}
