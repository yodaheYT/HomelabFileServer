package Server.Database;

import Server.Classes.User;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;
public class MongoDB {
    private static String connectUri = "mongodb://clever.local:29000/";
    public static MongoClient mongoClient;
    public static MongoDatabase primaryDB;
    public static void connect() {
        mongoClient = MongoClients.create(connectUri);
    }

    public static void initApp() {
        primaryDB = mongoClient.getDatabase("Primary");
    }

    public static BsonValue createUser(User user) {
        MongoCollection<Document> collection = primaryDB.getCollection("Users");
        try {
            InsertOneResult result = collection.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("Username", user.USERNAME)
                    .append("Password", user.PASSWORD)
                    .append("Email", user.EMAIL)
                    .append("Usergroup", user.USERGROUP)
                    .append("Token", user.giveToken()));
            return result.getInsertedId();
        } catch (MongoException me) {
            System.err.println(me);
        }
        return null;
    }
    public static User getUser(String key, String value) {
        MongoCollection<Document> collection = primaryDB.getCollection("Users");
        try {
            Document doc = collection
                    .find(eq(key, value))
                    .sort(Sorts.ascending("_id"))
                    .first();
            if (doc != null) {
                String USERNAME = doc.getString("Username");
                String PASSWORD = doc.getString("Password");
                String EMAIL = doc.getString("Email");
                int USERGROUP = doc.getInteger("Usergroup");
                User user = new User(USERNAME, PASSWORD, EMAIL, USERGROUP);
                return user;
            } else {
                return null;
            }
        } catch (MongoException me) {
            System.err.println(me);
        }
        return null;
    }
}
