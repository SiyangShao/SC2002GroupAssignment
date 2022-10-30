package Boundary;

import java.util.Scanner;

public class CineplexUI {
	private final Scanner sc;
	
	public CineplexUI(Scanner sc) {
		this.sc = sc;
	}
	
	public void HandleCinemaUI() {
		System.out.println("What would you like to do?");
		System.out.println("1. Create/View/Update/Remove Cineplex");
		System.out.println("2. Create/View/Update/Remove Cinema for Cineplex");
		System.out.println("3. Handle Showtimes for Cinema");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			break;
		}
	}
}
