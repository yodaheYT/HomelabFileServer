package Server.Database;

import Server.Classes.User;

import java.sql.*;
import java.util.HashMap;

import static Server.Main.logHandler;

public class SQLite {
    Connection c = null;
    public void connect() {

        try {
            String url = "jdbc:sqlite:data/main.db";
            c = DriverManager.getConnection(url);
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public void initApp(/*String Name, HashMap<String, String> hm*/) {
        String sql = "CREATE TABLE IF NOT EXISTS " + /*Name*/ "shares" + "(\n"
                + "id INTEGER PRIMARY KEY";
        /*hm.forEach((i, v) -> {
            sql += ",\n";
        });*/
        sql += ");";
        try {
            Statement statement = c.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(String PASSWORD, String EMAIL, String USERNAME, int GROUP) {
        String sql = "INSERT INTO USERS(PASSWORD, EMAIL, USERNAME, USERGROUP) "
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, PASSWORD);
            statement.setString(2, EMAIL);
            statement.setString(3, USERNAME);
            statement.setInt(4, GROUP);

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isEmailUsedAlready(String EMAIL) {
        String sql = "SELECT * FROM USERS WHERE EMAIL = \"" + EMAIL + "\"";

        Statement statement;
        try {
            statement = c.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isUsernameUsedAlready(String Username) {
        String sql = "SELECT * FROM USERS WHERE USERNAME = \"" + Username + "\"";

        Statement statement;
        try {
            statement = c.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getUser(String key, String value) {
        String sql = "SELECT * FROM USERS WHERE " + key + " = \"" + value + "\"";

        Statement statement;
        try {
            statement = c.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }
}
