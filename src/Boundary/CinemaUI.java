package Boundary;

import Controller.CineplexManager;
import Model.Cinema;
import Model.Cineplex;

import java.util.ArrayList;
import java.util.Scanner;

public class CinemaUI {
	private final Scanner sc;
	public CinemaUI(Scanner sc) {
		this.sc = sc;

	}
	public void handleCinemaCRUD(int cineplexID) {
		Cineplex cineplex = CineplexManager.getInstance().getOneCineplex(cineplexID);
		if (cineplex == null) {
			System.out.println("Invalid Cineplex ID");
			return;
		}
		System.out.println("What would you like to do?");
		System.out.println("1. Add Cinema");
		System.out.println("2. View Cinemas");
		System.out.println("3. Update Cinema");
		System.out.println("4. Remove Cinema");
		int choice = sc.nextInt();
		switch (choice) {
			case 1:
				this.addCinema(cineplex);
				break;
			case 2:
				System.out.println("List of Cinemas:");
				listCinema(cineplex);
				break;
			case 3:
				this.updateCinema(cineplex);
				break;
			case 4:
				this.removeCinema(cineplex);
				break;
			default:
				break;
		}
	}

	private void addCinema(Cineplex cineplex) {
		System.out.println("Add Cinema for " + cineplex.getCineplexName());
		Cinema cinema = cineplex.addCinema(getInput_Name(), getInput_Type());
		System.out.println("Successfully added Cinema " + cinema.getCinemaName() + " for Cineplex " + cineplex.getCineplexName());
	}
	private void updateCinema(Cineplex cineplex) {
		System.out.println("Update Cinema");
		listCinema(cineplex);
		System.out.println("Which Cinema would you like to update?, (Enter 0 to exit)");
		int cinemaID = sc.nextInt();
		if (cinemaID == 0 ) return;
		System.out.println("What would you like to update?");
		System.out.println("1. Name");
		System.out.println("2. Type");
		System.out.println("3. Exit");
		int choice = sc.nextInt();
		Object value = null;
		switch (choice) {
			case 1:
				value = getInput_Name();
				break;
			case 2:
				value = getInput_Type();
				break;
			case 3:
				return;
			default:
				System.out.println("You did not select anything");
				updateCinema(cineplex);
				break;
		}
		Cinema cinema = cineplex.updateCinema(cinemaID, choice, value);
		if (cinema == null) {
			System.out.println("Invalid Cinema ID, Try again");
			updateCinema(cineplex);
		} else {
			System.out.println("Successfully updated Cinema " + cinema.getCinemaName());
		}
	}
	private void removeCinema(Cineplex cineplex) {
		System.out.println("Remove Cinema");
		listCinema(cineplex);
		System.out.println("Which Cinema would you like to remove?, (Enter 0 to exit)");
		int cinemaID = sc.nextInt();
		if (cinemaID == 0 ) return;
		Cinema cinema = cineplex.removeCinema(cinemaID);
		if (cinema == null) {
			System.out.println("Invalid Cinema ID, Try again");
			removeCinema(cineplex);
		} else {
			System.out.println("Successfully removed Cinema " + cinema.getCinemaName());
		}
	}
	private void listCinema(Cineplex cineplex) {
		ArrayList<Cinema> cinemas = cineplex.getCinemas();
		for (int i =0; i < cinemas.size();i++) {
			System.out.println(cinemas.get(i).getCinemaID() + ". " + cinemas.get(i).getCinemaName());
		}
	}

	private String getInput_Name() {
		System.out.println("Enter Cinema Name");
		String name = sc.next();
		return name;
	}
	private int getInput_Type() {
		System.out.println("Enter Cinema Type (1. PLATINUM, 2. GOLD, 3. NORMAL)");
		return sc.nextInt();
	}
}
