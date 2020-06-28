package formatter.user;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static java.lang.String.valueOf;

public class User {
    private String login;
    private String salt;
    private String hash;
    private int lisence;

    public User() {}

    public User(String login, String salt, String hash) {
        this.login = login;
        this.salt = salt;
        this.hash = Integer.toHexString(salt.concat(hash).hashCode());
    }

    public String getLogin() {
        return login;
    }

    public String getSalt() { return salt; }

    public String getHash() {
        return hash;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSalt(String salt) { this.salt = salt; }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
