package Boundary;

import Controller.UserManager;
import Model.User;

import java.util.Scanner;

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
        User u = this.GetMovieGoerInfo();
        System.out.println("\n========== Movie Goer ==========\n");
        System.out.println("Please select what you want to do:");
        System.out.println("1. Search movies");
        System.out.println("2. List movies");
        System.out.println("3. View movies details");
        System.out.println("4. Purchase movie tickets"); // Further branch to seats also
        System.out.println("5. View booking history");
        System.out.println("6. List top 5 movies by ticket sales");
        System.out.println("7. List top 5 movies by overall ratings");
        System.out.println("8. Exit");
        System.out.println("\n================================\n");

        int choice = sc.nextInt();
        // Getting user's choice, then deciding which options to show
        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            // Exiting
            case 8:
                System.out.println("Exiting...");
                break;
        }while(choice != 8);
    }
}
