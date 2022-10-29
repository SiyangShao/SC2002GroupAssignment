package Controller;

import Utils.Config;
import Utils.Saveable;

import java.io.*;
import java.util.ArrayList;

public abstract class ManagerBase implements Saveable {

    private String filePath;

    protected abstract void SaveObjects(ObjectOutputStream out) throws IOException;
    protected abstract void LoadObjects(ObjectInputStream in) throws ClassNotFoundException, IOException;

    @Override
    public void Save() {
        try {
            FileOutputStream fos = new FileOutputStream(this.filePath);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            this.SaveObjects(out);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void Load(String filepath) {
        this.filePath = filepath;
        try {
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream in = new ObjectInputStream(fis);
            this.LoadObjects(in);
            in.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
            this.Save();
        }
        catch (IOException e) {
            e.printStackTrace();
            this.Save();
        } catch (ClassNotFoundException e) {
            this.Save();
        }
    }
}
