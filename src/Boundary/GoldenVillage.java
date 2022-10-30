package Boundary;

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

		int choice;
		Scanner sc = new Scanner(System.in);
		UserSelectorUI userSelectorUI = new UserSelectorUI(sc);
		MovieUI movieUI = new MovieUI(sc);
		CinemaUI cinemaUI = new CinemaUI(sc);
		CineplexUI cineplexUI = new CineplexUI(sc,cinemaUI);
		// Displaying the main menu
		do {
			System.out.println("\n========== Main Menu ==========\n");
			System.out.println("Please select user type:");
			System.out.println("1. Cinema Staff");
			System.out.println("2. Movie Goer");
			System.out.println("3. Exit");
			System.out.println("\n===============================\n");

			// Getting user's choice, then deciding which options to show
			choice = sc.nextInt();
			sc.nextLine(); // To remove the "enter"
			switch (choice) {

			// If cinema staff
			case 1: {
				String userName, passWord;

				// Need to login first
				boolean Success = userSelectorUI.HandleCinemaStaffLogin();
				do {
					// If username and password match, then show the actions
					// Correct username = "USERNAME1", correct password = "PASSWORD123"
					if (Success) {
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
						case 1: {
							movieUI.HandleMovie();
							break;
						}

						case 2: {
							cineplexUI.HandleCineplexUI();
							break;
						}

						case 3: {
							break;
						}

						// Exiting
						case 4: {
							System.out.println("Exiting...");
							return;
						}
						}
					}

					// Else if the details are wrong, return
					else {
						System.out.println(
								"You have entered incorrect username / password, please rerun the program again");
						return;
					}
				} while (choice != 4);

				break;
			}

			// If movie goer
			case 2: {
				User u = userSelectorUI.HandleMovieGoer();
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
				choice = sc.nextInt();

				// Getting user's choice, then deciding which options to show
				switch (choice) {
				case 1: {
					break;
				}

				case 2: {
					break;
				}

				case 3: {
					break;
				}

				case 4: {
					break;
				}

				case 5: {
					break;
				}

				case 6: {
					break;
				}

				case 7: {
					break;
				}

				// Exiting
				case 8: {
					System.out.println("Exiting...");
					return;
				}
				}
				break;
			}

			// If exit
			case 3: {
				System.out.println("Exiting...");
				return;
			}

			// If user key in invalid number, ask them try again
			default: {
				System.out.println("Please key in valid number (1 - 3)");
			}
			}

		} while (choice < 1 || choice > 3);
	}
}
