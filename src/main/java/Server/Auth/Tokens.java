package Server.Auth;

import Server.Classes.User;

public class Tokens {
    public static String genToken(String Password, String Username) {
        String p1 = Password;
        String p2 = Hash.Hash(Username);

        return (p1 + p2);
    }


    /*public static Boolean TokenValid(String Token, User user) {
        String p1 = Hash.Hash(String.valueOf(user.USERGROUP));
        String p2 = user.GetPwd();
        String p3 = Hash.Hash(user.USERNAME);

        if (Token == (p1 + p2 + p3)) {
            return true;
        } else {
            return false;
        }
    }*/
}
