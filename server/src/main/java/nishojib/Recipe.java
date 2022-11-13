package nishojib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private int id;
    private String title;
    private String image;
    private int servings;
    private int healthScore;
    private boolean cheap;
    private boolean glutenFree;
    private boolean dairyFree;
    private int readyInMinutes;
    private String instructions;
    private String summary;

    public Recipe(int id, String title, String image, int servings, int healthScore, int cheap, int glutenFree, int dairyFree, int readyInMinutes, String instructions, String summary) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.servings = servings;
        this.healthScore = healthScore;
        this.cheap = cheap == 1;
        this.glutenFree = glutenFree == 1;
        this.dairyFree = dairyFree == 1;
        this.readyInMinutes = readyInMinutes;
        this.instructions = instructions;
        this.summary = summary;
    }

    public static List<Recipe> findAll() throws SQLException {
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

        return recipes;
    }
}
