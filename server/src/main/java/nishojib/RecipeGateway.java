package nishojib;

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
}
