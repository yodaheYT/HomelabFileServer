package Server.Classes;

import Server.Auth.Hash;
import Server.Auth.Tokens;

public class User {
    public String USERNAME;
    private String PASSWORD;
    public String EMAIL;
    public int USERGROUP;

    private String TOKEN;

    public User(String username, String password, String email, int userGroup) {
        USERNAME = username;
        PASSWORD = password;
        EMAIL = email;
        USERGROUP = userGroup;

        TOKEN = Tokens.genToken(userGroup, password, username);
    }

    public boolean Auth(String enter) {
        if (enter.equals(PASSWORD)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean TokenAuth(String token) {
        if (token == TOKEN) {
            return true;
        } else {
            return false;
        }
    }

    public String giveToken() {
        return TOKEN;
    }
}
