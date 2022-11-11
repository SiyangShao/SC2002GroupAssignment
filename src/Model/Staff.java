package Model;

import java.io.Serializable;

/**
 Represents a staff.
 A staff can have username and hashed password for secure login
 @author  Soh Wee Kiat
 @version 1.0
 @since   20 October 2022
 */
public class Staff implements Serializable {

    private String Username;
    private String HashedPassword;

    public Staff(String Username, String HashedPassword)
    {
        this.Username = Username;
        this.HashedPassword = HashedPassword;
    }

    public String getUsername() {
        return Username;
    }

    public String getHashedPassword() {
        return HashedPassword;
    }
}
