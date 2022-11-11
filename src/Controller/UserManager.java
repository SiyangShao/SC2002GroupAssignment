package Controller;

import Model.Staff;
import Model.User;
import Utils.Config;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 Controller class for users and staff
 Manages list of staffs and users
 @version 1.0
 @since   20 October 2022
 */
public class UserManager extends ManagerBase {

    /**
     * The only instance of itself, UserManager
     */
    private static UserManager instance = null;

    /**
     * The ArrayList which contains all the Staffs
     */
    private ArrayList<Staff> Staffs;

    /**
     * The ArrayList which contains all the Users
     */
    private ArrayList<User> Users;

    /**
     * To ensure there is only one instance of itself, the UserManager
     * @return the only UserManager
     */
    public static UserManager getInstance()
    {
        if (instance == null) instance = new UserManager();
        return instance;
    }

    /**
     * Creates a UserManager.
     * Adding the default Staff details into the ArrayList Staffs
     */
    private UserManager()
    {
        this.Users = new ArrayList<>();
        this.Staffs = new ArrayList<>();

        this.Staffs.add(new Staff("admin", this.GenerateHashedPassword("admin")));
    }

    /**
     * Checks the correctness of Username and Password inputted
     * @param Username Username of the user's login details
     * @param Password Password of the user's login deails
     * @return True if the Username and Password matches the details of the ArrayList Staffs
     */
    public boolean Login(String Username, String Password)
    {
        String HashedPw = this.GenerateHashedPassword(Password);
        for(var s : this.Staffs){
            if (s.getUsername().equals(Username))
                return s.getHashedPassword().equals(HashedPw);
        }
        return false;
    }

    /**
     * Gets User based on the Email keyed in
     * @param Email Email to be checked
     * @return User which the Email matches
     */
    public User GetUserByEmail(String Email)
    {
        var r = this.Users.stream().filter(u -> u.getEmail().equals(Email)).findFirst();
        if (r.isPresent())
            return r.get();
        return null;
    }

    /**
     * Adds User to the ArrayList of Users
     * @param Email The Email of the User being added
     * @param Name The Name of the User being added
     * @param Phone The Phone number of the User being added
     * @return The added User
     */
    public User AddUser(String Email, String Name, String Phone)
    {
        User NewUser = new User(this.Users.size() + 1, Email, Name, Phone);
        this.Users.add(NewUser);
        this.Save();
        return NewUser;
    }

    /**
     * Gets User based on the UserID
     * @param UserID UserID of the User to be searched
     * @return The User which matches the UserID
     */
    public User GetUserById(int UserID) {
    	for (User u : Users) {
    		if (u.getUserId() == UserID) return u;
    	}
    	return null;
    }

    /**
     * Generates the hashed version of the password inputted
     * @param Password The Password to be hashed
     * @return Hashed version of the password inputted
     */
    private String GenerateHashedPassword(String Password)
    {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] result = md.digest(Password.getBytes());
            String s_result = "";
            for (byte b : result) {
                String st = String.format("%02X", b);
                s_result += st;
            }
            return s_result;
        }catch (NoSuchAlgorithmException e){
        }
        return Password;
    }

    /**
     * Implements the abstract method from MangerBase.
     * Saves the Users and Staffs created
     * @param out ObjectOutputStream for writing any java objects to file
     */
    @Override
    protected void SaveObjects(ObjectOutputStream out) throws IOException
    {
        out.writeObject(this.Staffs);
        out.writeObject(this.Users);
    }

    /**
     * Implements the abstract method from MangerBase.
     * Load the Users and Staffs created
     * @param in ObjectInputStream for reading any java objects from file
     */
    @Override
    protected void LoadObjects(ObjectInputStream in) throws ClassNotFoundException, IOException
    {
        this.Staffs = (ArrayList) in.readObject();
        this.Users = (ArrayList) in.readObject();
    }

    /**
     * Overriding the method from ManagerBase.
     * Load from the filepath
     * @param filepath String of the full file path to be written to
     */
    @Override
    public void Load(String filepath) {
        super.Load(filepath + Config.UserManagerFileName);
    }
}
