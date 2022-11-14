package nishojib;

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
            conn.close();
            return rs;
        } catch (SQLException e) {
            throw new GatewayException("Error occurred reading ingredients from data source");
        }
    }

}
