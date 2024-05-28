package Server.API.Users;

import Server.Auth.Hash;
import Server.Auth.UserPermLevels;
import Server.Classes.User;
import Server.Main;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static Server.Main.*;

public class CreateUser implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        logHandler.info("Recieving request on /api/createUser from " + exchange.getRemoteAddress().toString());

        InputStream is = exchange.getRequestBody();
        String result = null;

        int rCode = 200;
        String response = "200";
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

                    if (mongoDB.getUser("Email", jsonNode.get("EMAIL").asText().toLowerCase()) != null) {
                        logHandler.warning("EMAIL TAKEN");
                        rCode = 400;
                    } else {
                        if (mongoDB.getUser("USERNAME", jsonNode.get("Username").asText().toLowerCase()) != null) {
                            logHandler.warning("USERNAME TAKEN");
                            rCode = 400;
                        } else {
                            String password = Hash.Hash(jsonNode.get("PASSWORD").asText());
                            String username = jsonNode.get("USERNAME").asText();
                            String email = jsonNode.get("EMAIL").asText().toLowerCase();
                            int UserGroup = jsonNode.get("USERGROUP").asInt();

                            String creatorAuth = jsonNode.get("CREATORAUTH").asText();

                            User user = mongoDB.getUser("Token", creatorAuth);

                            if (user.Auth(creatorAuth) && user.USERGROUP == UserPermLevels.ROOT) {
                                User user2 = new User(username, password, email, UserGroup);
                                mongoDB.createUser(user2);

                                response = "success";
                                rCode = 200;

                            } else {
                                logHandler.warning("NO PERMISSION OR USER OR AUTH FAILED");
                                rCode = 401;
                            }
                        }
                    }
                } else {
                    logHandler.warning("NO JSON FOUND");
                }
            }
        } catch (IOException e) {
            logHandler.error(e.toString());
            throw new RuntimeException(e);
        }
        exchange.sendResponseHeaders(rCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
