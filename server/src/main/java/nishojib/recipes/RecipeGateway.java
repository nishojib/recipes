package nishojib.tdg;

import nishojib.exceptions.GatewayException;

import java.sql.*;

public class RecipeGateway {
    public Connection connect(String dbUrl) throws SQLException {
        String url = "jdbc:sqlite:db/" + dbUrl;
        return DriverManager.getConnection(url);
    }

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

    public ResultSet findOneById(int recipeId) throws GatewayException {
        try {
            Connection conn = connect("recipes.sqlite");
            String sql = "SELECT * FROM recipes WHERE id=" + recipeId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            throw new GatewayException("Error occurred reading recipes from data source");
        }
    }

    public void deleteById(int recipeId) throws GatewayException {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            conn = connect("recipes.sqlite");
            conn.setAutoCommit(false);

            String sqlForDeletingRecipeIngredients = "DELETE FROM recipes_ingredients WHERE recipeId=" + recipeId;
            String sqlForDeletingRecipe = "DELETE FROM recipes WHERE id=" + recipeId;

            pstmt1 = conn.prepareStatement(sqlForDeletingRecipeIngredients, Statement.SUCCESS_NO_INFO);
            int rowAffected = pstmt1.executeUpdate();

            if (rowAffected != -2) {
                conn.rollback();
            }

            pstmt2 = conn.prepareStatement(sqlForDeletingRecipe);
            pstmt2.executeUpdate();

            conn.commit();

        } catch (SQLException e1) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException e2) {
                throw new GatewayException("Error rolling back.");
            }
            throw new GatewayException("Error occurred reading recipes from data source.");
        } finally {
            try {
                if (pstmt1 != null) {
                    pstmt1.close();
                }
                if (pstmt2 != null) {
                    pstmt2.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e3) {
                throw new GatewayException("Error closing connection.");
            }
        }
    }

}
