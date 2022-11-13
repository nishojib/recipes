package nishojib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static spark.Spark.get;

public class App {
    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        get("recipes", (req, res) -> {
            res.type("application/json");

            Connect connection = new Connect();
            Connection conn = connection.connect("recipes.sqlite");

            String sql = "SELECT * FROM recipes";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ArrayList<Recipe> recipes = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String image = rs.getString("image");
                int servings = rs.getInt("servings");
                int healthScore = rs.getInt("healthScore");
                int cheap = rs.getInt("cheap");
                int glutenFree = rs.getInt("glutenFree");
                int dairyFree = rs.getInt("dairyFree");
                int readyInMinutes = rs.getInt("readyInMinutes");
                String instructions = rs.getString("instructions");
                String summary = rs.getString("summary");

                recipes.add(new Recipe(id, title, image, servings, healthScore, cheap, glutenFree, dairyFree, readyInMinutes, instructions, summary));
            }

            conn.close();
            return gson.toJson(recipes);

        });

        get("tracks", (req, res) -> {
            res.type("application/json");

            Connect connection = new Connect();
            Connection conn = connection.connect("chinook.db");

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
