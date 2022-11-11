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

    /**
     * Username of the staff
     */
    private String Username;
    /**
     * Hashed password of the staff
     */
    private String HashedPassword;

    /**
     * The constructor of the Staff
     *
     * @param Username        The username of the staff
     * @param HashedPassword  The hashed password of the staff
     */
    public Staff(String Username, String HashedPassword)
    {
        this.Username = Username;
        this.HashedPassword = HashedPassword;
    }

    /**
     * Get the Username of staff
     *
     * @return The staff's username
     */
    public String getUsername() {
        return Username;
    }

    /**
     * Get the hashed password of staff
     *
     * @return The hashed string of staff
     */
    public String getHashedPassword() {
        return HashedPassword;
    }
}
