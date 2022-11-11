package nishojib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static spark.Spark.get;

public class App {
    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        get("tracks", (req, res) -> {
            res.type("application/json");

            Connect connection = new Connect();
            Connection conn = connection.connect();

            String sql = "SELECT TrackId, Name, Composer, Milliseconds, Bytes, UnitPrice FROM tracks";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Track track = null;

            while (rs.next()) {
                int trackId = rs.getInt("TrackId");
                String name = rs.getString("Name");
                String composer = rs.getString("Composer");
                int milliseconds = rs.getInt("Milliseconds");
                int bytes = rs.getInt("Bytes");
                double unitPrice = rs.getDouble("UnitPrice");

                track = new Track(trackId, name, composer, milliseconds, bytes, unitPrice);
            }

            conn.close();

            if (track != null) {
                return gson.toJson(track);
            }

            return "";
        });
    }
}
