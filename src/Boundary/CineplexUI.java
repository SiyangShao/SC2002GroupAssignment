package Boundary;

import Controller.CineplexManager;
import Controller.MovieManager;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;

import java.util.ArrayList;
import java.util.Scanner;

public class CineplexUI {
	private final Scanner sc;
	private final CinemaUI cinemaUI;
	
	public CineplexUI(Scanner sc, CinemaUI ui) {
		this.sc = sc;
		this.cinemaUI = ui;
	}
	
	public void HandleCineplexUI() {
		int choice;
		Cineplex cineplex;
		do {
			System.out.println("What would you like to do?");
			System.out.println("1. Create/View/Update/Remove Cineplex");
			System.out.println("2. Create/View/Update/Remove Cinema for Cineplex");
			System.out.println("3. Handle Showtimes for Cinema");
			System.out.println("4. Exit");

			choice = sc.nextInt();
			switch (choice) {
				case 1:
					HandleCineplexCRUD();
					break;
				case 2:
					cineplex = getInput_Cineplex();
					if (cineplex == null) {
						System.out.println("Invalid Cineplex ID.");
						break;
					}
					cinemaUI.handleCinemaCRUD(cineplex);
					break;
				case 3:
					cineplex = getInput_Cineplex();
					if (cineplex == null) {
						System.out.println("Invalid Cineplex ID.");
						break;
					}
					cinemaUI.handleShowTimes(cineplex);
					break;
				case 4:
					break;
				default:
					System.out.println("Invalid Choice.");
					break;
			}
		} while (choice != 4);

	}

	private void HandleCineplexCRUD() {
		System.out.println("1. Add Cineplex");
		System.out.println("2. View Cineplexs");
		System.out.println("3. Update Cineplex");
		System.out.println("4. Remove Cineplex");
		int choice = sc.nextInt();
		switch (choice) {
			case 1:
				this.addCineplex();
				break;
			case 2:
				System.out.println("List of Cineplexs:");
				this.listCineplex();
				break;
			case 3:
				this.updateCineplex();
				break;
			case 4:
				this.removeCineplex();
				break;
			default:
				break;
		}
	}
	
	private void addCineplex() {
		System.out.println("Add Cineplex");
		Cineplex cineplex = CineplexManager.getInstance().addCineplex(getInput_Name(), getInput_Location());
		System.out.println("Successfully added Cineplex: " + cineplex.getCineplexName());
		
	}
	private void updateCineplex() {
		System.out.println("Update Cineplex");
		listCineplex();
		System.out.println("Which Cineplex would you like to update?, (Enter 0 to exit)");
		int cineplexID = sc.nextInt();
		if (cineplexID == 0 ) return;
		System.out.println("What would you like to update?");
		System.out.println("1. Name");
		System.out.println("2. Location");
		System.out.println("3. Exit");
		int choice = sc.nextInt();
		Object value = null;
		switch (choice) {
			case 1:
				value = getInput_Name();
				break;
			case 2:
				value = getInput_Location();
				break;
			case 3:
				return;
			default:
				System.out.println("You did not select anything");
				updateCineplex();
				break;
		}
		Cineplex c = CineplexManager.getInstance().updateCineplex(cineplexID, choice, value);
		if (c == null) {
			System.out.println("Invalid Cineplex ID, Try again");
			updateCineplex();
		} else {
			System.out.println("Successfully updated Cineplex " + c.getCineplexName());
		}
	}
	private void removeCineplex() {
		System.out.println("Remove Cineplex");
		listCineplex();
		System.out.println("Which Cineplex would you like to remove?, (Enter 0 to exit)");
		int cineplexID = sc.nextInt();
		if (cineplexID == 0 ) return;
		Cineplex cineplex = CineplexManager.getInstance().removeCineplex(cineplexID);
		if (cineplex == null) {
			System.out.println("Invalid Cineplex ID, Try again");
			removeCineplex();
		} else {
			System.out.println("Successfully removed Cineplex " + cineplex.getCineplexName());
		}
	}
	private void listCineplex() {
		ArrayList<Cineplex> cineplexes = CineplexManager.getInstance().getCineplexes();
		if (cineplexes == null || cineplexes.size() == 0) {
			System.out.println("No Cineplexs Found.");
			return;
		}
		for (int i =0; i < cineplexes.size();i++) {
			System.out.println(cineplexes.get(i).getCineplexID() + ". " + cineplexes.get(i).getCineplexName());
		}
	}

	private String getInput_Name() {
		System.out.println("Enter Cineplex Name");
		String name = sc.next();
		return name;
	}
	private String getInput_Location() {
		System.out.println("Enter Cineplex Location");
		String loc = sc.next();
		return loc;
	}

	private Cineplex getInput_Cineplex() {
		System.out.println("List of Available Cineplexs");
		listCineplex();
		System.out.println("Enter Cineplex ID");
		int cineplexID = sc.nextInt();
		return CineplexManager.getInstance().getOneCineplex(cineplexID);

	}
	
}
