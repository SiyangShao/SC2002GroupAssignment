package Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.Cineplex;
import Utils.Config;

public class CineplexManager extends ManagerBase {
	private ArrayList<Cineplex> Cineplexes;
	private static CineplexManager instance;
	
	public CineplexManager() {
		
		
	}
	
	public static CineplexManager getInstance() {
		if (instance == null) {
			instance = new CineplexManager();
		}
		return instance;
	}
	public void addCineplex() {
		
	}
	
	public void removeCineplex() {
		
	}
	
	public int getSize() {
		return this.Cineplexes.size();
	}
    @Override
    protected void SaveObjects(ObjectOutputStream out) throws IOException
    {
        out.writeObject(this.Cineplexes);
    }

    @Override
    protected void LoadObjects(ObjectInputStream in) throws ClassNotFoundException, IOException
    {
        this.Cineplexes = (ArrayList) in.readObject();
    }

    @Override
    public void Load(String filepath) {
        super.Load(filepath + Config.CineplexManagerFileName);
    }
}
