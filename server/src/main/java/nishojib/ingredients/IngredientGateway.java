package nishojib.ingredients;

import nishojib.core.exceptions.GatewayException;
import nishojib.ingredients.models.IngredientDTO;

import java.sql.*;

/**
 * The Table Data Gateway for an Ingredient
 */
public class IngredientGateway {
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
     * The method that returns all the ingredients from the SQL table
     * @return The ResultSet containing all the ingredients
     * @throws GatewayException
     */
    public ResultSet findAll() throws GatewayException {
        try {
            Connection conn = this.connect("recipes.sqlite");
            String sql = "SELECT * FROM ingredients";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            throw new GatewayException("Error occurred reading ingredients from data source");
        }
    }

    /**
     * The method that returns one ingredient from the SQL table by id
     * @param ingredientId The id of the ingredient
     * @return The ResultSet containing one ingredient
     * @throws GatewayException
     */
    public ResultSet findOneById(int ingredientId) throws GatewayException {
        try {
            Connection conn = this.connect("recipes.sqlite");
            String sql = "SELECT * FROM ingredients WHERE id=" + ingredientId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            throw new GatewayException("Error occurred reading ingredient from data source");
        }
    }

    /**
     * The method that deletes an ingredient from the SQL table by ingredient id
     * @param ingredientId The id of the ingredient
     * @throws GatewayException
     */
    public void deleteById(int ingredientId) throws GatewayException {
        Connection conn = null;

        try {
            conn = connect("recipes.sqlite");
            conn.setAutoCommit(false);

            String sqlForDeletingRecipeIngredients = "DELETE FROM recipes_ingredients WHERE ingredientId=" + ingredientId;
            String sqlForDeletingIngredients = "DELETE FROM ingredients WHERE id=" + ingredientId;

            PreparedStatement pstmt1 = conn.prepareStatement(sqlForDeletingRecipeIngredients);
            pstmt1.executeUpdate();

            PreparedStatement pstmt2 = conn.prepareStatement(sqlForDeletingIngredients);
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
            throw new GatewayException("Error occurred deleting ingredient from data source");
        }
    }

    /**
     * The method that creates an ingredient in the SQL table
     * @param ingredient The ingredient transfer object
     * @throws GatewayException
     */
    public int create(IngredientDTO ingredient) throws GatewayException {
        try {
            Connection conn = connect("recipes.sqlite");

            String sqlForCreatingIngredients = "INSERT INTO ingredients (name, amount, unit, original) VALUES (?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sqlForCreatingIngredients, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, ingredient.getName());
            pstmt.setFloat(2, ingredient.getAmount());
            pstmt.setString(3, ingredient.getUnit());
            pstmt.setString(4, ingredient.getOriginal());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            int ingredientId = 0;
            if (rs.next()) {
                ingredientId = rs.getInt(1);
            }

            pstmt.close();

            return ingredientId;
        } catch (SQLException e1)    {
            throw new GatewayException("Error occurred creating ingredient in data source");
        }
    }

    /**
     * The method that updates an ingredient by its id in the SQL table
     * @param ingredientId The id of the ingredient
     * @param ingredient The ingredient transfer object
     * @throws GatewayException
     */
    public void update(int ingredientId, IngredientDTO ingredient) throws GatewayException {
        try {
            Connection conn = connect("recipes.sqlite");

            String sqlForUpdatingIngredients = "UPDATE ingredients SET name = ?, amount = ?, unit = ?, original = ? WHERE id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sqlForUpdatingIngredients, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, ingredient.getName());
            pstmt.setFloat(2, ingredient.getAmount());
            pstmt.setString(3, ingredient.getUnit());
            pstmt.setString(4, ingredient.getOriginal());
            pstmt.setInt(5, ingredientId);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e1)    {
            throw new GatewayException("Error occurred updating ingredient in data source");
        }
    }

}
