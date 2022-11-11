package Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Model.Movie;
import Utils.Config;
import Utils.Saveable;

/**
 Represents a CalendarManager that manages all the Holidays
 Extends from ManagerBase
 @author  Soh Wee Kiat
 @version 1.0
 @since   20 October 2022
 */

public class CalendarManager extends ManagerBase {
	/**
	 * The ArrayList which contains all the Holidays
	 */
	private ArrayList<LocalDateTime> Holidays;

	/**
	 * The only instance of itself, CalendarManager
	 */
	private static CalendarManager instance = null;

	/**
	 * Creates a new CalendarManager
	 */
	public CalendarManager() {
		this.Holidays = new ArrayList<>();
	}

	/**
	 * Checks whether the datetime is HOLIDAY or NORMAL
	 * @param datetime The datetime to be checked
	 * @return The DateType based on the datetime
	 */
	public Model.DateType DateType(LocalDateTime datetime) {
		if(Holidays.contains(datetime)){
			return Model.DateType.HOLIDAY;
		}
		return Model.DateType.NORMAL;
	}

	/**
	 * Adds a datetime into the ArrayList of LocalDateTime to be counted as Holiday
	 * @param datetime The datetime to be added to be counted as Holiday
	 */
    public void AddHoliday(LocalDateTime datetime){
        Holidays.add(datetime);
    }

	/**
	 * Checks whether a particular datetime is a Holiday
	 * @param datetime The datetime to be checked
	 * @return True when the particular datetime is a Holiday
	 */
	public boolean isHoliday(LocalDateTime datetime) {
		if (Holidays.contains(datetime)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Removes a particular datetime from the ArrayList of LocalDateTime.
	 * Such that it is no longer counted as a Holiday.
	 * @param datetime The datetime to be removed from the ArrayList of LocalDateTime for Holidays
	 */
    public void RemoveHoliday(LocalDateTime datetime){
        Holidays.remove(datetime);
    }

	/**
	 * Implements the abstract method from MangerBase.
	 * Saves the Holidays created
	 * @param out ObjectOutputStream for writing any java objects to file
	 */
	@Override
	protected void SaveObjects(ObjectOutputStream out) throws IOException
	{
		out.writeObject(this.Holidays);
	}

	/**
	 * Implements the abstract method from MangerBase.
	 * Load the Holidays created
	 * @param in ObjectInputStream for reading any java objects from file
	 */
	@Override
	protected void LoadObjects(ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		this.Holidays = (ArrayList) in.readObject();
	}

	/**
	 * Overriding the method from ManagerBase.
	 * Load from the filepath
	 * @param filepath String of the full file path to be written to
	 */
	@Override
	public void Load(String filepath) {
		super.Load(filepath + Config.CalendarManagerFileName);
	}

	/**
	 * To ensure there is only one instance of itself, the CalendarManager
	 * @return the only CalendarManager
	 */
	public static CalendarManager getInstance() {
		if (instance == null) {
			instance = new CalendarManager();
		}
		return instance;
	}
}
