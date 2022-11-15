package nishojib.recipes;

import nishojib.core.exceptions.GatewayException;
import nishojib.recipes.models.RecipeDTO;

import java.sql.*;

/**
 * The Table Data Gateway for a Recipe
 */
public class RecipeGateway {
    /**
     * The connection method that creates and returns a database connection
     * @param dbUrl The URL of the database
     * @return The database connection
     * @throws SQLException
     */
    public Connection connect(String dbUrl) throws SQLException {
        String url = "jdbc:sqlite:db/" + dbUrl;
        return DriverManager.getConnection(url);
    }

    /**
     * The method that returns all the recipes from the SQL table
     * @return The ResultSet containing all the recipes
     * @throws GatewayException
     */
    public ResultSet findAll() throws GatewayException {
        try {
            Connection conn = this.connect("recipes.sqlite");
            String sql = "SELECT * FROM recipes";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            throw new GatewayException("Error occurred reading recipes from data source.");
        }
    }

    /**
     * The method that returns one recipe from the SQL table by id
     * @param recipeId The id of the recipe
     * @return The ResultSet containing one recipe
     * @throws GatewayException
     */
    public ResultSet findOneById(int recipeId) throws GatewayException {
        try {
            Connection conn = connect("recipes.sqlite");
            String sql = "SELECT * FROM recipes WHERE id=" + recipeId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            throw new GatewayException("Error occurred reading recipe from data source");
        }
    }

    /**
     * The method that returns all the ingredient ids from the SQL table of a
     * particular recipe
     * @param recipeId The id of the recipe
     * @return The ResultSet containing all the ingredient ids
     * @throws GatewayException
     */
    public ResultSet findIngredientIdsFromRecipeId(int recipeId) throws GatewayException {
        try {
            Connection conn = connect("recipes.sqlite");
            String sql = "SELECT ingredientId FROM recipes_ingredients WHERE recipeId=" + recipeId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            throw new GatewayException("Error occurred reading recipe from data source");
        }
    }

    /**
     * The method that deletes a recipes from the SQL table by recipe id
     * and its corresponding ingredients ids
     * @param recipeId The id of the recipe
     * @throws GatewayException
     */
    public void deleteById(int recipeId) throws GatewayException {
        Connection conn = null;

        try {
            conn = connect("recipes.sqlite");
            conn.setAutoCommit(false);

            String sqlForDeletingRecipeIngredients = "DELETE FROM recipes_ingredients WHERE recipeId=" + recipeId;
            String sqlForDeletingRecipe = "DELETE FROM recipes WHERE id=" + recipeId;

            PreparedStatement pstmt1 = conn.prepareStatement(sqlForDeletingRecipeIngredients);
            pstmt1.executeUpdate();

            PreparedStatement pstmt2 = conn.prepareStatement(sqlForDeletingRecipe);
            pstmt2.executeUpdate();

            conn.commit();

            pstmt1.close();
            pstmt2.close();
        } catch (SQLException e1) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException e2) {
                throw new GatewayException("Error rolling back");
            }
            throw new GatewayException("Error occurred deleting recipe from data source");
        }
    }

    /**
     * The method that creates a recipe in the SQL table along with its ingredients
     * @param recipe The recipe transfer object
     * @throws GatewayException
     */
    public int create(RecipeDTO recipe) throws GatewayException {
        Connection conn = null;

        try {
            conn = connect("recipes.sqlite");
            conn.setAutoCommit(false);

            String sqlForCreatingRecipe = "INSERT INTO recipes (title, image, servings, healthScore, cheap, glutenFree, dairyFree, readyInMinutes, instructions, summary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sqlForCreatingRecipeAndIngredients = "INSERT INTO recipes_ingredients (recipeId, ingredientId) VALUES (?, ?)";

            PreparedStatement pstmt1 = conn.prepareStatement(sqlForCreatingRecipe, Statement.RETURN_GENERATED_KEYS);
            pstmt1.setString(1, recipe.getTitle());
            pstmt1.setString(2, recipe.getImage());
            pstmt1.setInt(3, recipe.getServings());
            pstmt1.setInt(4, recipe.getHealthScore());
            pstmt1.setBoolean(5, recipe.isCheap());
            pstmt1.setBoolean(6, recipe.isGlutenFree());
            pstmt1.setBoolean(7, recipe.isDairyFree());
            pstmt1.setInt(8, recipe.getReadyInMinutes());
            pstmt1.setString(9, recipe.getInstructions());
            pstmt1.setString(10, recipe.getSummary());

            int rowAffected = pstmt1.executeUpdate();

            ResultSet rs = pstmt1.getGeneratedKeys();
            int recipeId = 0;
            if (rs.next()) {
                recipeId = rs.getInt(1);
            }

            if (rowAffected != 1) {
                conn.rollback();
            }

            PreparedStatement pstmt2 = null;
            for (int ingredientId : recipe.getIngredientIds()) {
                pstmt2 = conn.prepareStatement(sqlForCreatingRecipeAndIngredients);
                pstmt2.setInt(1, recipeId);
                pstmt2.setInt(2, ingredientId);
                pstmt2.executeUpdate();
            }

            conn.commit();

            pstmt1.close();
            if (pstmt2 != null) pstmt2.close();
            return recipeId;
        } catch (SQLException e1) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException e2) {
                throw new GatewayException("Error rolling back");
            }
            throw new GatewayException("Error occurred creating recipe in data source");
        }
    }

    /**
     * The method that updates a recipe by its id in the SQL table along with its ingredients
     * @param recipeId The id of the recipe
     * @param recipe The recipe transfer object
     * @throws GatewayException
     */
    public void update(int recipeId, RecipeDTO recipe) throws GatewayException {
        Connection conn = null;

        try {
            conn = connect("recipes.sqlite");

            conn.setAutoCommit(false);

            String sqlForUpdatingRecipes = "UPDATE recipes SET title = ?, image = ?, servings = ?, healthScore = ?, cheap = ?, glutenFree = ?, dairyFree = ?, readyInMinutes = ?, instructions = ?, summary = ? WHERE id = ?";
            String sqlForDeletingRecipeIngredients = "DELETE FROM recipes_ingredients WHERE recipeId=" + recipeId;
            String sqlForCreatingRecipeAndIngredients = "INSERT INTO recipes_ingredients (recipeId, ingredientId) VALUES (?, ?)";

            PreparedStatement pstmt1 = conn.prepareStatement(sqlForUpdatingRecipes);
            pstmt1.setString(1, recipe.getTitle());
            pstmt1.setString(2, recipe.getImage());
            pstmt1.setInt(3, recipe.getServings());
            pstmt1.setInt(4, recipe.getHealthScore());
            pstmt1.setBoolean(5, recipe.isCheap());
            pstmt1.setBoolean(6, recipe.isGlutenFree());
            pstmt1.setBoolean(7, recipe.isDairyFree());
            pstmt1.setInt(8, recipe.getReadyInMinutes());
            pstmt1.setString(9, recipe.getInstructions());
            pstmt1.setString(10, recipe.getSummary());
            pstmt1.setInt(11, recipeId);

            int rowAffected = pstmt1.executeUpdate();

            if (rowAffected != 1) {
                conn.rollback();
            }

            PreparedStatement pstmt2 = conn.prepareStatement(sqlForDeletingRecipeIngredients);
            pstmt2.executeUpdate();

            PreparedStatement pstmt3 = null;
            for (int ingredientId : recipe.getIngredientIds()) {
                pstmt3 = conn.prepareStatement(sqlForCreatingRecipeAndIngredients);
                pstmt3.setInt(1, recipeId);
                pstmt3.setInt(2, ingredientId);
                pstmt3.executeUpdate();
            }

            conn.commit();

            pstmt1.close();
            pstmt2.close();
            if (pstmt3 != null) pstmt3.close();
        } catch (SQLException e1) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException e2) {
                throw new GatewayException("Error rolling back");
            }
            throw new GatewayException("Error occurred updating recipes in data source");
        }
    }
}
