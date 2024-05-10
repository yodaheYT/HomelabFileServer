package Server.API.Users;

import Server.Auth.Hash;
import Server.Auth.UserPermLevels;
import Server.Classes.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static Server.Main.logHandler;
import static Server.Main.sqLite;

public class LogIn implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        logHandler.info("Recieving request on /api/login from " + exchange.getRemoteAddress().toString());

        InputStream is = exchange.getRequestBody();
        String result = null;

        int rCode = 404;
        String response = "404";
        JsonNode jsonNode = null;
        try {
            if (is.available() > 0) {
                Scanner s = new Scanner(is).useDelimiter("\\A");
                result = s.hasNext() ? s.next() : "";

                ObjectMapper mapper = new ObjectMapper();

                try {
                    jsonNode = mapper.readTree(result);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (jsonNode != null) {
                    String email = jsonNode.get("EMAIL").asText();
                    String EnteredPwd = jsonNode.get("PASSWORD").asText();

                    ResultSet rs = sqLite.getUser("EMAIL", email.toLowerCase());

                    User user = new User(rs.getString("USERNAME"), rs.getString("PASSWORD"),
                            rs.getString("EMAIL"), rs.getInt("USERGROUP"));

                    if (user.Auth(Hash.Hash(EnteredPwd)) == true) {
                        response = user.giveToken();
                        rCode = 200;
                    }
                }
            }
        } catch (IOException e) {
            logHandler.error(e.toString());
            throw new RuntimeException(e);
        } catch (SQLException e) {
            logHandler.error(e.toString());
            throw new RuntimeException(e);
        }
        exchange.sendResponseHeaders(rCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
