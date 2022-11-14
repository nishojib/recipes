package nishojib.ingredients;

import nishojib.core.exceptions.GatewayException;

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
            throw new GatewayException("Error occurred reading ingredients from data source");
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
                throw new GatewayException("Error rolling back.");
            }
            throw new GatewayException("Error occurred deleting ingredients from data source.");
        }
    }

}
