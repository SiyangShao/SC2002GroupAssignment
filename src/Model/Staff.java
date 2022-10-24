package Model;

import java.io.Serializable;

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
