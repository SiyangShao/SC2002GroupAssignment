package Controller;

import Model.Staff;
import Model.User;
import Utils.Config;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class UserManager extends ManagerBase {
    private static UserManager instance = null;

    private ArrayList<Staff> Staffs;
    private ArrayList<User> Users;

    public static UserManager getInstance()
    {
        if (instance == null) instance = new UserManager();
        return instance;
    }

    private UserManager()
    {
        this.Users = new ArrayList<>();
        this.Staffs = new ArrayList<>();

        this.Staffs.add(new Staff("admin", this.GenerateHashedPassword("admin")));
    }

    public boolean Login(String Username, String Password)
    {
        String HashedPw = this.GenerateHashedPassword(Password);
        for(var s : this.Staffs){
            if (s.getUsername().equals(Username))
                return s.getHashedPassword().equals(HashedPw);
        }
        return false;
    }

    public User GetUserByEmail(String Email)
    {
        var r = this.Users.stream().filter(u -> u.getEmail().equals(Email)).findFirst();
        if (r.isPresent())
            return r.get();
        return null;
    }

    public User AddUser(String Email, String Name, String Phone)
    {
        User NewUser = new User(this.Users.size() + 1, Email, Name, Phone);
        this.Users.add(NewUser);
        this.Save();
        return NewUser;
    }
    
    public User GetUserById(int UserID) {
    	for (User u : Users) {
    		if (u.getUserId() == UserID) return u;
    	}
    	return null;
    }

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

    @Override
    protected void SaveObjects(ObjectOutputStream out) throws IOException
    {
        out.writeObject(this.Staffs);
        out.writeObject(this.Users);
    }

    @Override
    protected void LoadObjects(ObjectInputStream in) throws ClassNotFoundException, IOException
    {
        this.Staffs = (ArrayList) in.readObject();
        this.Users = (ArrayList) in.readObject();
    }

    @Override
    public void Load(String filepath) {
        super.Load(filepath + Config.UserManagerFileName);
    }
}
