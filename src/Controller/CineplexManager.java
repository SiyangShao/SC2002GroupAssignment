package Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.Cineplex;
import Utils.Config;

/**
 Represents a CineplexManager that manages all the Cineplexes
 Extends from ManagerBase
 @author  Soh Wee Kiat
 @version 1.0
 @since   20 October 2022
 */

public class CineplexManager extends ManagerBase {
	/**
	 * The ArrayList which contains all the Cineplexes
	 */
	private ArrayList<Cineplex> Cineplexes;
	/**
	 * The only instance of itself, CineplexManager
	 */
	private static CineplexManager instance;

	/**
	 * Creates a new CineplexManager
	 */
	public CineplexManager() {
		this.Cineplexes = new ArrayList<>();
		
	}

	/**
	 * To ensure there is only one instance of itself, the CineplexManager
	 * @return the only CineplexManager
	 */
	public static CineplexManager getInstance() {
		if (instance == null) {
			instance = new CineplexManager();
		}
		return instance;
	}

	/**
	 * Adds a Cineplex into the ArrayList of Cineplexes
	 * @param name Name of the Cinplex to be added
	 * @param loc Location of the Cineplex to be added
	 * @return The Cineplex created
	 */
	public Cineplex addCineplex(String name, String loc) {
		Cineplex cineplex = new Cineplex(name, loc);
		this.Cineplexes.add(cineplex);
		this.Save();
		return cineplex;
	}

	/**
	 * Update Cineplex details using Cineplex's ID
	 * @param ID Cinplex's ID to be updated
	 * @param choice User's choice to decide which details of the Cineplex to be updated
	 * @param value The new value of the Cineplex detail to be updated to
	 * @return The updated Cineplex
	 */
	public Cineplex updateCineplex(int ID, int choice, Object value) {
		Cineplex toUpdate = null;
		for (int i =0; i < this.getSize(); i++) {
			if (this.getInstance().Cineplexes.get(i).getCineplexID() == ID) {
				toUpdate = this.getInstance().Cineplexes.get(i);
			}
		}
		if (toUpdate == null) return null;
		else {
			switch (choice) {
				case 1:
					toUpdate.setCineplexName((String) value);
					break;
				case 2:
					toUpdate.setLocation((String) value);
					break;
			}
		}
		this.Save();
		return toUpdate;
	}

	/**
	 * Removes the Cineplex from the ArrayList of Cineplexes
	 * @param cID The Cineplex's cID to be removed
	 * @return The removed Cineplex
	 */
	public Cineplex removeCineplex(int cID) {
		Cineplex cineplex = null;
		for (int i =0; i<this.Cineplexes.size(); i++) {
			if (this.Cineplexes.get(i).getCineplexID() == cID) {
				cineplex = this.Cineplexes.get(i);
				this.Cineplexes.remove(i);
			}
		}
		this.Save();
		return cineplex;
	}

	/**
	 * Gets the ArrayList of Cineplexes
	 * @return The ArrayList of Cineplexes
	 */
	public ArrayList<Cineplex> getCineplexes() {
		return this.Cineplexes;
	}

	/**
	 * Gets one Cineplex from the ArrayList of Cineplexes using cineplexID
	 * @param cineplexID The cineplexID of the Cineplex to be returned
	 * @return The Cineplex that matches the cineplexID
	 */
	public Cineplex getOneCineplex(int cineplexID) {
		Cineplex cineplex = null;
		for (int i =0; i < this.Cineplexes.size(); i++) {
			if (this.Cineplexes.get(i).getCineplexID() == cineplexID) cineplex = this.Cineplexes.get(i);
		}
		return cineplex;
	}

	/**
	 * Get the CinemaType based on the cineplexID and cinemaID
	 * @param cineplexID The cineplexID of the Cineplex to be searched
	 * @param cinemaID The cinemaID of the Cinema to be searched
	 * @return CinemaType of the Cineplex that matches the cineplexID and cinemaID
	 */
	public Model.CinemaType getCinemaType(int cineplexID, int cinemaID) {
		Cineplex cineplex = this.getOneCineplex(cineplexID);
		if (cineplex == null) return null;
		else {
			return cineplex.getCinemaType(cinemaID);
		}
	}

	/**
	 * Gets the size of the ArrayList of Cineplexes
	 * @return the size of the ArrayList of Cineplexes
	 */
	public int getSize() {
		return this.Cineplexes.size();
	}

	/**
	 * Implements the abstract method from MangerBase.
	 * Saves the Cineplexes created
	 * @param out ObjectOutputStream for writing any java objects to file
	 */
    @Override
    protected void SaveObjects(ObjectOutputStream out) throws IOException
    {
        out.writeObject(this.Cineplexes);
    }

	/**
	 * Implements the abstract method from MangerBase.
	 * Load the Cineplexes created
	 * @param in ObjectInputStream for reading any java objects from file
	 */
    @Override
    protected void LoadObjects(ObjectInputStream in) throws ClassNotFoundException, IOException
    {
        this.Cineplexes = (ArrayList) in.readObject();
    }

	/**
	 * Overriding the method from ManagerBase.
	 * Load from the filepath
	 * @param filepath String of the full file path to be written to
	 */
    @Override
    public void Load(String filepath) {
        super.Load(filepath + Config.CineplexManagerFileName);
    }
}
