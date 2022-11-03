package Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Model.Movie;
import Utils.Config;
import Utils.Saveable;

public class CalendarManager extends ManagerBase {
	private ArrayList<LocalDateTime> Holidays;
	private static CalendarManager instance;

	public CalendarManager() {

	}

	public Model.DateType DateType(LocalDateTime datetime) {
		if(Holidays.contains(datetime)){
			return Model.DateType.HOLIDAY;
		}
		return Model.DateType.NORMAL;
	}

    public void AddHoliday(LocalDateTime datetime){
        Holidays.add(datetime);
    }

    public void RemoveHoliday(LocalDateTime datetime){
        Holidays.remove(datetime);
    }
	@Override
	protected void SaveObjects(ObjectOutputStream out) throws IOException
	{
		out.writeObject(this.Holidays);
	}

	@Override
	protected void LoadObjects(ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		this.Holidays = (ArrayList) in.readObject();
	}

	@Override
	public void Load(String filepath) {
		super.Load(filepath + Config.CalendarManagerFileName);
	}

	public static CalendarManager getInstance() {
		if (instance == null) {
			instance = new CalendarManager();
		}
		return instance;
	}

	// add remove etc

}
