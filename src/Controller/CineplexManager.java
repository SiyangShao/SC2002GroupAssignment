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
		this.Cineplexes = new ArrayList<>();
		
	}
	
	public static CineplexManager getInstance() {
		if (instance == null) {
			instance = new CineplexManager();
		}
		return instance;
	}
	public Cineplex addCineplex(String name, String loc) {
		Cineplex cineplex = new Cineplex(name, loc);
		this.Cineplexes.add(cineplex);
		this.Save();
		return cineplex;
	}
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
		return toUpdate;
	}
	public Cineplex removeCineplex(int cID) {
		Cineplex cineplex = null;
		for (int i =0; i<this.Cineplexes.size(); i++) {
			if (this.Cineplexes.get(i).getCineplexID() == cID) {
				cineplex = this.Cineplexes.get(i);
				this.Cineplexes.remove(i);
			}
		}
		return cineplex;
	}
	public ArrayList<Cineplex> getCineplexes() {
		return this.Cineplexes;
	}

	public Cineplex getOneCineplex(int cineplexID) {
		Cineplex cineplex = null;
		for (int i =0; i < this.Cineplexes.size(); i++) {
			if (this.Cineplexes.get(i).getCineplexID() == cineplexID) cineplex = this.Cineplexes.get(i);
		}
		return cineplex;
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
