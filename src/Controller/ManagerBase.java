package Controller;
import Utils.Saveable;
import java.io.*;

/**
 A base class for all Managers.
 Implements Saveable interface and abstract function for saving/loading objects
 @version 1.0
 @since   20 October 2022
 */
public abstract class ManagerBase implements Saveable {

    /**
     * the file path of the serialized manager file
     */
    private String filePath;

    /**
     * Abstraction method that child class needs to implement for saving
     * @param out ObjectOutputStream for writing any java objects to file
     */
    protected abstract void SaveObjects(ObjectOutputStream out) throws IOException;

    /**
     * Abstraction method that child class needs to implement for loading
     * @param in ObjectInputStream for reading any java objects from file
     */
    protected abstract void LoadObjects(ObjectInputStream in) throws ClassNotFoundException, IOException;

    /**
     * General saving method body for all managers
     */
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

    /**
     * General loading method body for all managers
     * @param filepath String of the full file path to be written to
     */
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
