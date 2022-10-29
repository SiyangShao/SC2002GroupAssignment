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

    public void DisplayUserSelection()
    {
        System.out.println("\n========== Main Menu ==========\n");
        System.out.println("Please select user type:");
        System.out.println("1. Cinema Staff");
        System.out.println("2. Movie Goer");
        System.out.println("3. Exit");
        System.out.println("\n===============================\n");

        int choice = sc.nextInt();
        sc.nextLine();      // To remove the "enter"
        switch(choice) {
            case 1:
                if (!this.HandleCinemaStaffLogin()) {
                    System.out.println("You have entered incorrect username / password, please rerun the program again");
                }
                break;
            case 2:
            {
                User u = this.HandleMovieGoer();
                break;
            }
        }
    }

    public User HandleMovieGoer()
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
}
