package Boundary;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MovieManager;
import Model.Movie;
import Model.MovieStatus;

public class MovieUI {
	private final Scanner sc;

	public MovieUI(Scanner sc) {
		this.sc = sc;
	}

	public void HandleMovieUI() {
		System.out.println("What would you like to do?");
		System.out.println("1. Add New Movie");
		System.out.println("2. Edit Movie");
		System.out.println("3. Delete Movie");
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
			listMovies();
			break;
		default:
			break;
		}
	}

	private void addMovie() {
		// String title,MovieType type, MovieStatus status, String desc, String direc
		System.out.println("Create New Movie");
		Movie m = MovieManager.getInstance().addMovie(getInput_Title(), getInput_Type(), getInput_MovieRating(), getInput_Status(),
				getInput_Desc(), getInput_Duration(), getInput_Direc());
		System.out.println("Successfully added new Movie: " + m.getTitle());
	}

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

	public void listMovies() {
		ArrayList<Movie> movies = MovieManager.getInstance().getMovies();
		if (movies == null) {
			System.out.println("No Movies Found.");
			return;
		}
		for (int i = 0; i < movies.size(); i++) {
			Movie oneMovie = movies.get(i);
			if (oneMovie.getStatus() != MovieStatus.END_OF_SHOWING)
				System.out.println(oneMovie.getMovieID() + ". " + oneMovie.getTitle());
		}
	}

	private int getInput_Type() {
		System.out.println("Enter Movie Type (1. 3D, 2. BlockBuster, 3. Anime)");
		return sc.nextInt();
	}

	private String getInput_Title() {
		System.out.println("Enter Movie Title");
		return sc.next();
	}

	private int getInput_Status() {
		System.out.println("Enter Movie Status (1. COMING SOON, 2. PREVIEW, 3. NOW SHOWING, 4. END OF SHOWING)");
		return sc.nextInt();
	}

	private int getInput_MovieRating() {
		System.out.println("Enter Movie Rating (1. G, 2. PG, 3. R18, 4. R21)");
		return sc.nextInt();
	}
	private int getInput_Duration() {
		System.out.println("Enter Movie Duration in Minutes");
		return sc.nextInt();
	}
	private String getInput_Desc() {
		System.out.println("Enter Movie Description");
		return sc.next();
	}

	private ArrayList<String> getInput_Cast() {
		ArrayList<String> cast = new ArrayList<String>();
		System.out.println("Enter How Many Cast Members:");
		int amount = sc.nextInt();
		for (int i = 0; i < amount; i++) {
			System.out.println("Enter Cast Name:");
			cast.add(sc.next());
		}
		return cast;
	}

	private String getInput_Direc() {
		System.out.println("Enter Movie Director");
		return sc.next();
	}

}
