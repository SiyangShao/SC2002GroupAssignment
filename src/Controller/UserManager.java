package Controller;

import Model.Staff;
import Model.User;
import Utils.Config;
import Utils.Saveable;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class UserManager implements Saveable {

    private ArrayList<Staff> Staffs;
    private ArrayList<User> Users;

    public UserManager()
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
    public void Save(String filepath) {
        try {
            FileOutputStream fos = new FileOutputStream(filepath + Config.UserManagerFileName);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(this.Staffs);
            out.writeObject(this.Users);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void Load(String filepath) {
        try {
            FileInputStream fis = new FileInputStream(filepath + Config.UserManagerFileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            this.Staffs = (ArrayList) in.readObject();
            this.Users = (ArrayList) in.readObject();
            in.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
            this.Save(filepath);
        }
        catch (IOException e) {
            e.printStackTrace();
            this.Save(filepath);
        } catch (ClassNotFoundException e) {
            this.Save(filepath);
        }
    }
}
