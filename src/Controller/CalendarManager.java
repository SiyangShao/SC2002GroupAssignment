package Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Model.Movie;
import Utils.Saveable;

public class CalendarManager implements Saveable {
	private ArrayList<LocalDateTime> Holidays;

	public CalendarManager() {

	}

	public Movie.DateType DateType(LocalDateTime datetime) {
		if(Holidays.contains(datetime)){
			return Movie.DateType.HOLIDAY;
		}
		return Movie.DateType.NORMAL;
	}

    public void AddHoliday(LocalDateTime datetime){
        Holidays.add(datetime);
    }

    public void RemoveHoliday(LocalDateTime datetime){
        Holidays.remove(datetime);
    }
	@Override
	public void Save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Load(String filepath) {
		// TODO Auto-generated method stub

	}

	// add remove etc

}
