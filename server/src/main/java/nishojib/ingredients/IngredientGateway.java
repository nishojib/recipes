package nishojib.ingredients;

import nishojib.core.exceptions.GatewayException;
import nishojib.ingredients.models.IngredientDTO;

import java.sql.*;

public class IngredientGateway {
    public Connection connect(String dbUrl) throws SQLException {
        String url = "jdbc:sqlite:db/" + dbUrl;
        return DriverManager.getConnection(url);
    }

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

    public void create(IngredientDTO ingredientDTO) throws GatewayException {
        try {
            Connection conn = connect("recipes.sqlite");

            String sqlForCreatingIngredients = "INSERT INTO ingredients (name, amount, unit, original) VALUES (?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sqlForCreatingIngredients, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, ingredientDTO.getName());
            pstmt.setFloat(2, ingredientDTO.getAmount());
            pstmt.setString(3, ingredientDTO.getUnit());
            pstmt.setString(4, ingredientDTO.getOriginal());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e1)    {
            throw new GatewayException("Error occurred creating ingredient in data source");
        }
    }

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
