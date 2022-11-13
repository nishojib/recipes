package nishojib;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDataMapper {
    public synchronized static List<Recipe> findAll() throws DataMapperException {
        try {
            ResultSet rs = RecipeGateway.findAll();

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

            return recipes;
        } catch (GatewayException | SQLException e) {
            throw new DataMapperException("Error occurred reading recipes from data source");
        }

    }
}
