package nishojib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecipeGateway {
    public static ResultSet findAll() throws GatewayException {
        try {
            Connect connection = new Connect();
            Connection conn = connection.connect("recipes.sqlite");
            String sql = "SELECT * FROM recipes";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            throw new GatewayException("Error occurred reading recipes from data source");
        }

    }
}
