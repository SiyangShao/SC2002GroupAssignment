package Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Model.DateType;
import Utils.Key;
import Utils.Saveable;

public class CalendarManager implements Saveable {
	private ArrayList<Key<LocalDateTime, DateType>> Dates;
	
	public CalendarManager() {
		
	}

	@Override
	public void Save(String filepath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Load(String filepath) {
		// TODO Auto-generated method stub
		
	}
	
	// add remove etc
	
}
