package Model;

import java.io.Serializable;

public class User implements Serializable {

    private String UserId;
    private String Email;
    private String Name;
    private String Phone;

    public User(String UserId, String Email, String Name, String Phone)
    {
        this.UserId = UserId;
        this.Email = Email;
        this.Name = Name;
        this.Phone = Phone;
    }

    public String getUserId() {
        return UserId;
    }

    public String getEmail() {
        return Email;
    }

    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
