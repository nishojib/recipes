package nishojib.recipes;

import nishojib.ingredients.IngredientDataMapper;
import nishojib.ingredients.models.Ingredient;
import nishojib.recipes.models.Recipe;
import nishojib.core.exceptions.DataMapperException;
import nishojib.core.exceptions.GatewayException;
import nishojib.recipes.models.RecipeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Data Mapper for the Recipe
 */
public class RecipeDataMapper {
    /**
     * Access to the recipe table data gateway
     */
    RecipeGateway recipeGateway;
    /**
     * Access to the ingredients data mapper
     */
    IngredientDataMapper ingredientMapper;

    /**
     * The initializer for the recipe data mapper
     */
    public RecipeDataMapper() {
        recipeGateway = new RecipeGateway();
        ingredientMapper = new IngredientDataMapper();
    }

    /**
     * The method that finds and returns all recipes
     * @return A List of Recipe objects
     * @throws DataMapperException
     */
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

    /**
     * The method that finds and returns one recipe by id
     * @param recipeId The recipe id of the recipe
     * @return A Recipe Object that was found using the recipe id
     * @throws DataMapperException
     */
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

    /**
     * The method that finds and deletes one recipe by id
     * @param recipeId The recipe id of the recipe
     * @return {true} if a recipe is deleted, {false} otherwise
     * @throws DataMapperException
     */
    public synchronized boolean deleteById(int recipeId) throws DataMapperException {
        try {
            recipeGateway.deleteById(recipeId);
            return true;
        } catch (GatewayException e) {
            throw new DataMapperException("Error occurred deleting recipe from data source");
        }
    }

    /**
     * The method that creates a recipe
     * @param recipe The recipe data transfer object
     * @throws DataMapperException
     */
    public int create(RecipeDTO recipe) throws DataMapperException {
        try {
            return recipeGateway.create(recipe);
        } catch (GatewayException e) {
            throw new DataMapperException("Error occurred creating recipe in data source");
        }
    }

    /**
     * The method that updates a recipe based on a recipeId
     * @param recipeId The id of the recipe
     * @param recipe The recipe data transfer object
     * @throws DataMapperException
     */
    public void update(int recipeId, RecipeDTO recipe) throws DataMapperException {
        try {
            recipeGateway.update(recipeId, recipe);
        } catch (GatewayException e) {
            throw new DataMapperException("Error occurred updating recipe in data source");
        }
    }

    /**
     * The ingredients of a particular recipe
     * @param recipeId The id of the recipe
     * @return A list of Ingredient Object
     * @throws DataMapperException
     */
    public List<Ingredient> findIngredientsOfOneById(int recipeId) throws DataMapperException {
        try {
            ArrayList<Integer> ingredientIds = new ArrayList<>();
            ResultSet rs = recipeGateway.findIngredientIdsFromRecipeId(recipeId);

            while (rs.next()) {
                int id = rs.getInt("ingredientId");
                ingredientIds.add(id);
            }

            ArrayList<Ingredient> ingredients = new ArrayList<>();
            for (Integer id: ingredientIds) {
                Ingredient ingredient = ingredientMapper.findOneById(id);
                ingredients.add(ingredient);
            }

            return ingredients;
        } catch (GatewayException | SQLException e) {
            System.out.println(e.getMessage());
            throw new DataMapperException("Error occurred reading recipes from data source");
        }
    }

    /**
     * The method that helps create a Recipe object from ResultSet
     * @param rs The ResultSet acquired from the table data gateway
     * @return A Recipe Object corresponding to the ResultSet
     * @throws SQLException
     */
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
