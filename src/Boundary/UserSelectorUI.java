package Boundary;

import Controller.UserManager;
import Controller.MovieManager;
import Model.Ticket;
import Model.TicketType;
import Model.User;
import Model.Movie;
import Model.MovieStatus;

import java.util.*;

public class UserSelectorUI {

    private final Scanner sc;

    public UserSelectorUI(Scanner sc) {
        this.sc = sc;
    }

    public void DisplayUserTypeSelection() {
        System.out.println("\n========== Main Menu ==========\n");
        System.out.println("Please select user type:");
        System.out.println("1. Cinema Staff");
        System.out.println("2. Movie Goer");
        System.out.println("3. Exit");
        System.out.println("\n===============================\n");
    }

    public boolean HandleCinemaStaffLogin() {
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

    public void DisplayStaffActions(MovieUI movieUI, CineplexUI cineplexUI) {
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
                    movieUI.HandleSystemSettings();
                    break;
                // Exiting
                case 4:
                    System.out.println("Exiting...");
                    break;
            }
        } while (choice != 4);
    }

    private User GetMovieGoerInfo() {
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


    public void DisplayMovieGoerActions(MovieUI movieUI) {
        ArrayList<Movie> listOfMovies = MovieManager.getInstance().getMovies();
        User u = this.GetMovieGoerInfo();
        do {
            System.out.println("\n========== Movie Goer ==========\n");
            System.out.println("Please select what you want to do:");
            System.out.println("1. Search movies");     // View movie details can be selected from this option
            System.out.println("2. List movies");       // View movie details can be selected from this option
            System.out.println("3. Purchase movie tickets"); // Further branch to seats also
            System.out.println("4. View booking history");
            System.out.println("5. Add Review");
            System.out.println("6. List top 5 movies by ticket sales");
            System.out.println("7. List top 5 movies by overall ratings");
            System.out.println("8. Exit");
            System.out.println("\n================================\n");

            int choice = sc.nextInt();
            // Getting user's choice, then deciding which options to show
            switch (choice) {
                case 1:
                    // If current listOfMovies have no movies inside
                    if (listOfMovies.size() == 0) {
                        System.out.println("Sorry there are currently no movies!");
                        break;
                    }

                    // Getting the keyword from the user to be searched
                    System.out.printf("Please enter the keyword to be searched: ");
                    sc.nextLine();        // To remove the carriage return character
                    String keyWord = sc.nextLine();

                    System.out.println("Displaying the list of movies with keyword \"" + keyWord + "\"...");
                    System.out.println("=====================================");
                    System.out.println("MOVIE ID\t\tMOVIE TITLE");
                    System.out.println("=====================================");

                    // Searching through the listOfMovies for titles have contain the keyword, then displaying it
                    boolean foundMovieID1 = false;
                    for (int i = 0; i < listOfMovies.size(); i++) {
                        if (listOfMovies.get(i).getTitle().toLowerCase().contains(keyWord.toLowerCase())) {
                            System.out.println(i + 1 + ".\t\t\t" + listOfMovies.get(i).getTitle());
                            foundMovieID1 = true;
                        }
                    }

                    // If cannot find any movies that contain the keyword
                    if (!foundMovieID1) {
                        System.out.println("Sorry there are currently no movies that match " + keyWord + "!");
                        break;
                    }
                    // User can select the movie by the MovieID to view the details of the movie
                    System.out.printf("Please select the Movie ID to view the details of the movie: ");
                    int movieChoice1 = sc.nextInt();

                    // Searching through the listOfMovies for that MovieID, then printing out the details
                    foundMovieID1 = false;
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
                    // If current listOfMovies have no movies inside
                    if (listOfMovies.size() == 0) {
                        System.out.println("Sorry there are currently no movies!");
                        break;
                    }

                    System.out.println("Displaying the list of movies...");
                    System.out.println("=====================================");
                    System.out.println("MOVIE ID\t\tMOVIE TITLE");
                    System.out.println("=====================================");


                    // Looping through the ArrayList to get all the movie titles
                    for (int i = 0; i < listOfMovies.size(); i++) {
                        System.out.println(i + 1 + ".\t\t\t" + listOfMovies.get(i).getTitle());
                    }
                    // User can select the movie by the MovieID to view the details of the movie
                    System.out.print("Please select the Movie ID to view the details of the movie: ");
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
                    System.out.println("Here are the list of movies:");
                    System.out.println("=====================================");
                    System.out.println("MOVIE ID\t\tMOVIE TITLE");
                    System.out.println("=====================================");
                    for (Movie listOfMovie : listOfMovies) {
                    	if (listOfMovie.getStatus() == MovieStatus.END_OF_SHOWING) {
                    		
                    	}
                    	else if (listOfMovie.getStatus() == MovieStatus.COMING_SOON) {
                    		System.out.println(listOfMovie.getMovieID() + ".\t\t\t" + listOfMovie.getTitle() + "(COMING SOON)");
                    	}
                    	else {
                    		System.out.println(listOfMovie.getMovieID() + ".\t\t\t" + listOfMovie.getTitle());
                    	}
                    }
                    System.out.println("Please select the Movie ID to view the details of the movie: ");
                    int movieID = sc.nextInt();
                    Movie movie = MovieManager.getInstance().getOneMovie(movieID);
                    if (movie.getStatus() == MovieStatus.COMING_SOON) {
                    	System.out.println("Movie is not out yet.");
                    	break;
                    	
                    }
                    if (movie == null) {
                        System.out.println("Movie ID not found.");
                        break;
                    }
                    System.out.println("Here are the list of movie slots:");
                    System.out.println("=====================================");
                    System.out.println("MOVIE SLOT ID\t\tMOVIE SLOT\t\tShow Time\t\tCinemaID\t\tSeats Rest");
                    System.out.println("=====================================");
                    for (Model.MovieSlot movieSlot : movie.getSlots()) {
                        System.out.println(movieSlot.getMovieSlotID() + ".\t\t\t" + movieSlot.getMovieName() + "\t\t" + movieSlot.getShowTime() + "\t\t" + movieSlot.getCinemaID() + "\t\t" + movieSlot.remainSeatNumber());
                    }
                    System.out.println("Please enter the movie slot ID you want to book: ");
                    int movieSlotID = sc.nextInt();
                    Model.MovieSlot movieSlot = movie.getSlot(movieSlotID);
                    if (movieSlot == null) {
                        System.out.println("Movie Slot ID not found");
                        break;
                    }
                    System.out.println("Please enter the number of tickets you want to book: ");
                    int numOfTickets = sc.nextInt();
                    ArrayList<Model.TicketType> ticketTypes = new ArrayList<>();
                    ArrayList<Integer> seatNumbers = new ArrayList<>();
                    for (int i = 0; i < numOfTickets; i++) {
                        System.out.println("Please enter the ticket type you want to book: ");
                        System.out.println("1. Adult");
                        System.out.println("2. Child");
                        System.out.println("3. Senior");
                        System.out.println("4. Student");
                        int ticketType = sc.nextInt();
                        switch (ticketType) {
                            case 1:
                                ticketTypes.add(Model.TicketType.ADULT);
                                break;
                            case 2:
                                ticketTypes.add(Model.TicketType.CHILD);
                                break;
                            case 3:
                                ticketTypes.add(Model.TicketType.SENIOR_CITIZEN);
                                break;
                            case 4:
                                ticketTypes.add(Model.TicketType.STUDENT);
                                break;
                        }

                        movieSlot.showSeats();

                        System.out.println("Please enter the seat number you want to book: ");
                        int seatNumber = sc.nextInt() - 1;
                        seatNumbers.add(seatNumber);
                        movie.Tix();
                    }
                    u.bookSeats(seatNumbers, ticketTypes, movieSlot);
                    break;
                case 4:
                    u.viewBookingHistory();
                    break;
                case 5:
                	System.out.println("List of Movies");
                	movieUI.listMovies();
                	System.out.println("Which movie would you like to review?");
                	movieID = sc.nextInt();
                	movie = MovieManager.getInstance().getOneMovie(movieID);
                	u.setMovieRating(movie);
                	
                	break;
                case 6:
                    System.out.println("Top 5 Movies based on Ticket Sales(lowest to highest)");
                    HashMap<String, Integer> TixTable = new HashMap<String, Integer>();                    
                    for(Movie movieInList : listOfMovies){
                        //System.out.println();
                        TixTable.put(movieInList.getTitle(), movieInList.getTix());
                    }
                    List<Map.Entry<String, Integer> > nlist = new LinkedList<Map.Entry<String, Integer> >(TixTable.entrySet());
                    Collections.sort(nlist, new Comparator<Map.Entry<String, Integer> >() {
                        public int compare(Map.Entry<String, Integer> o1,
                                           Map.Entry<String, Integer> o2)
                        {
                            return (o1.getValue()).compareTo(o2.getValue());
                        }
                    });
                    HashMap<String, Integer> sortMap = new LinkedHashMap<String, Integer>();
                    for (Map.Entry<String, Integer> aa : nlist) {
                        sortMap.put(aa.getKey(), aa.getValue());
                    }
                    int counter = 1;
                    Iterator<String> ite = sortMap.keySet().iterator();
                    while (ite.hasNext() && counter < 6) {
                        String key = ite.next();
                        System.out.println(counter +". "+ key +": " + sortMap.get(key));
                        counter++;
                    }
                    
                    break;
                case 7:
                    System.out.println("Top 5 Movies based on Review Ratings(lowest to highest)");
                    HashMap<String, Double> reviewRatingTable = new HashMap<String, Double>();                    
                    for(Movie movieInList : listOfMovies){
                        reviewRatingTable.put(movieInList.getTitle(), movieInList.getAvgRating());
                    }
                    List<Map.Entry<String, Double> > list = new LinkedList<Map.Entry<String, Double> >(reviewRatingTable.entrySet());
                    Collections.sort(list, new Comparator<Map.Entry<String, Double> >() {
                        public int compare(Map.Entry<String, Double> o1,
                                           Map.Entry<String, Double> o2)
                        {
                            return (o1.getValue()).compareTo(o2.getValue());
                        }
                    });
                    HashMap<String, Double> sortedMap = new LinkedHashMap<String, Double>();
                    for (Map.Entry<String, Double> aa : list) {
                        sortedMap.put(aa.getKey(), aa.getValue());
                    }
                    int count = 1;
                    Iterator<String> itr = sortedMap.keySet().iterator();
                    while (itr.hasNext() && count < 6) {
                        String key = itr.next();
                        System.out.println(count +". "+ key +": " + sortedMap.get(key));
                        count++;
                    }
                    break;
                // Exiting
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Please enter a valid choice");
                    break;
            }
        }
        while (true);
    }
}
