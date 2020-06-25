package formatter.user;

import java.io.File;
import java.util.List;

public class User {
    private String login;
    private String hash;
    private int lisence;
    private List<String > logins;

    public User() {}

    public User(String login, String hash) {
        this.login = login;
        this.hash = String.valueOf(hash.hashCode());
    }

    public String getLogin() {
        return login;
    }

    public String getHash() {
        return hash;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<String> getLogins() {
        return logins;
    }
}
