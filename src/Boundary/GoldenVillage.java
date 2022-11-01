package Boundary;

import Controller.CineplexManager;
import Controller.MovieManager;
import Controller.UserManager;
import Model.*;

import java.nio.file.Paths;
import java.util.Scanner;

public class GoldenVillage {
	public static void main(String[] args) {
		// Declaration of variables
		String CurPath = Paths.get("").toAbsolutePath().toString() + "/";
		UserManager.getInstance().Load(CurPath);
		MovieManager.getInstance().Load(CurPath);
		CineplexManager.getInstance().Load(CurPath);
		int choice;
		Scanner sc = new Scanner(System.in);
		UserSelectorUI userSelectorUI = new UserSelectorUI(sc);
		MovieUI movieUI = new MovieUI(sc);
		CinemaUI cinemaUI = new CinemaUI(sc);
		CineplexUI cineplexUI = new CineplexUI(sc,cinemaUI);
		// Displaying the main menu
		do {
			userSelectorUI.DisplayUserTypeSelection();
			choice = sc.nextInt();
			sc.nextLine();      // To remove the "enter"
			switch(choice) {
				case 1:
					if (!userSelectorUI.HandleCinemaStaffLogin()) {
						System.out.println("You have entered incorrect username / password, please rerun the program again");
						choice = 3;//force exit
					}else{
						userSelectorUI.DisplayStaffActions(movieUI, cineplexUI);
					}
					break;
				case 2:
					userSelectorUI.DisplayMovieGoerActions();
					break;
				case 3:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Please key in valid number (1 - 3)");
					break;
			}
		} while (choice != 3);
	}
}
