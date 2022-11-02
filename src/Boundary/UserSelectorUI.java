package Boundary;

import Controller.UserManager;
import Controller.MovieManager;
import Model.User;
import Model.Movie;

import java.util.Scanner;
import java.util.ArrayList;

public class UserSelectorUI {

    private final Scanner sc;

    public UserSelectorUI(Scanner sc)
    {
        this.sc = sc;
    }

    public void DisplayUserTypeSelection()
    {
        System.out.println("\n========== Main Menu ==========\n");
        System.out.println("Please select user type:");
        System.out.println("1. Cinema Staff");
        System.out.println("2. Movie Goer");
        System.out.println("3. Exit");
        System.out.println("\n===============================\n");
    }

    public boolean HandleCinemaStaffLogin()
    {
        String userName, passWord;

        // Need to login first
        System.out.println("\n========== Cinema Staff ==========\n");
        System.out.println("Please enter staff username:");
        userName = sc.nextLine();
        System.out.println("Please enter staff password:");
        passWord = sc.nextLine();
        System.out.println("userName = " + userName + ", passWord = " + passWord);

        return UserManager.getInstance().Login(userName, passWord);
    }

    public void DisplayStaffActions(MovieUI movieUI, CineplexUI cineplexUI)
    {
        int choice = 0;
        do {
            System.out.println("\n========== Cinema Staff ==========\n");
            System.out.println("Please select what you want to do:");
            System.out.println("1. Create/Update/Remove movie listing");
            System.out.println("2. Create/Update/Remove cinema showtimes and the movies to be shown");
            System.out.println("3. Configure system settings");
            System.out.println("4. Exit");
            System.out.println("\n==================================\n");

            choice = sc.nextInt();
            // Getting user's choice, then deciding which options to show
            switch (choice) {
                case 1:
                    movieUI.HandleMovieUI();
                    break;
                case 2:
                    cineplexUI.HandleCineplexUI();
                    break;
                case 3:
                    break;
                // Exiting
                case 4:
                    System.out.println("Exiting...");
                    break;
            }
        }while(choice != 4);
    }

    private User GetMovieGoerInfo()
    {
        System.out.println("\n========== Movie Goer ==========\n");
        System.out.println("Please enter Email:");
        String email = sc.nextLine();
        User u = UserManager.getInstance().GetUserByEmail(email);
        if (u != null)
            return u;
        System.out.println("Please enter Name:");
        String name = sc.nextLine();
        System.out.println("Please enter phone:");
        String phone = sc.nextLine();
        return UserManager.getInstance().AddUser(email, name, phone);
    }


    public void DisplayMovieGoerActions()
    {
        ArrayList<Movie> listOfMovies = MovieManager.getInstance().getMovies();

        User u = this.GetMovieGoerInfo();
        System.out.println("\n========== Movie Goer ==========\n");
        System.out.println("Please select what you want to do:");
        System.out.println("1. Search movies");     // View movie details can be selected from this option
        System.out.println("2. List movies");       // View movie details can be selected from this option
        System.out.println("3. Purchase movie tickets"); // Further branch to seats also
        System.out.println("4. View booking history");
        System.out.println("5. List top 5 movies by ticket sales");
        System.out.println("6. List top 5 movies by overall ratings");
        System.out.println("7. Exit");
        System.out.println("\n================================\n");

        int choice = sc.nextInt();
        // Getting user's choice, then deciding which options to show
        switch (choice) {
            case 1:
                // Getting the keyword from the user to be searched
                System.out.printf("Please enter the keyword to be searched: ");
                String keyWord = sc.nextLine();

                System.out.println("Displaying the list of movies with keyword \"" + keyWord + "\"...");
                System.out.println("=====================================");
                System.out.println("MOVIE ID\t\tMOVIE TITLE");
                System.out.println("=====================================");

                // Searching through the listOfMovies for titles have contain the keyword, then displaying it
                for (int i = 0; i < listOfMovies.size(); i++) {
                    if (listOfMovies.get(i).getTitle().contains(keyWord)) {
                        System.out.println(i + 1 + ".\t\t\t" + listOfMovies.get(i).getTitle());
                    }
                }

                // User can select the movie by the MovieID to view the details of the movie
                System.out.printf("Please select the Movie ID to view the details of the movie: ");
                int movieChoice1 = sc.nextInt();

                // Searching through the listOfMovies for that MovieID, then printing out the details
                boolean foundMovieID1 = false;
                for (int i = 0; i < listOfMovies.size(); i++) {
                    if (listOfMovies.get(i).getMovieID() == movieChoice1) {
                        foundMovieID1 = true;
                        listOfMovies.get(i).printDetails();
                    }
                }

                if (!foundMovieID1) {
                    System.out.println("Please enter a valid Movie ID");
                }

                // Possible branch to show the show timings and then buying the tickets???
                break;
            case 2:
                System.out.println("Displaying the list of movies...");
                System.out.println("=====================================");
                System.out.println("MOVIE ID\t\tMOVIE TITLE");
                System.out.println("=====================================");

                // Looping through the ArrayList to get all the movie titles
                for (int i = 0; i < listOfMovies.size(); i++) {
                    System.out.println(i + 1 + ".\t\t\t" + listOfMovies.get(i).getTitle());
                }
                // User can select the movie by the MovieID to view the details of the movie
                System.out.printf("Please select the Movie ID to view the details of the movie: ");
                int movieChoice2 = sc.nextInt();

                // Searching through the listOfMovies for that MovieID, then printing out the details
                boolean foundMovieID2 = false;
                for (int i = 0; i < listOfMovies.size(); i++) {
                    if (listOfMovies.get(i).getMovieID() == movieChoice2) {
                        foundMovieID2 = true;
                        listOfMovies.get(i).printDetails();
                    }
                }

                if (!foundMovieID2) {
                    System.out.println("Please enter a valid Movie ID");
                }

                // Possible branch to show the show timings and then buying the tickets???
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            // Exiting
            case 7:
                System.out.println("Exiting...");
                break;
        }while(choice != 8);
    }
}
