package nishojib.mappers;

import nishojib.models.Recipe;
import nishojib.tdg.RecipeGateway;
import nishojib.exceptions.DataMapperException;
import nishojib.exceptions.GatewayException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDataMapper {
    RecipeGateway recipeGateway;

    public RecipeDataMapper() {
        recipeGateway = new RecipeGateway();
    }

    public synchronized List<Recipe> findAll() throws DataMapperException {
        try {
            ResultSet rs = recipeGateway.findAll();
            ArrayList<Recipe> recipes = new ArrayList<>();

            while (rs.next()) {
                Recipe recipe = getRecipeFromResultSet(rs);
                recipes.add(recipe);
            }

            return recipes;
        } catch (GatewayException | SQLException e) {
            System.out.println(e.getMessage());
            throw new DataMapperException("Error occurred reading recipes from data source");
        }
    }

    public synchronized Recipe findOneById(int recipeId) throws DataMapperException {
        try {
            ResultSet rs = recipeGateway.findOneById(recipeId);

            while (rs.next()) {
                return getRecipeFromResultSet(rs);
            }

            return null;
        } catch (GatewayException | SQLException e) {
            throw new DataMapperException("Error occurred reading recipe from data source");
        }
    }

    public synchronized boolean deleteById(int recipeId) throws DataMapperException {
        try {
            recipeGateway.deleteById(recipeId);
            return true;
        } catch (GatewayException e) {
            throw new DataMapperException("Error occurred reading recipes from data source");
        }
    }


    public static Recipe getRecipeFromResultSet(ResultSet rs) throws SQLException {
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

        return new Recipe(id, title, image, servings, healthScore, cheap, glutenFree, dairyFree, readyInMinutes, instructions, summary);
    }

}
