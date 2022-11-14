package nishojib;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDataMapper {
    IngredientGateway ingredientGateway;

    public IngredientDataMapper() {
        ingredientGateway = new IngredientGateway();
    }
    public synchronized List<Ingredient> findAll() throws DataMapperException {
        try {
            ResultSet rs = ingredientGateway.findAll();
            ArrayList<Ingredient> ingredients = new ArrayList<>();

            while (rs.next()) {
                Ingredient ingredient = getIngredientFromResultSet(rs);
                ingredients.add(ingredient);
            }

            return ingredients;
        } catch (GatewayException | SQLException e) {
            throw new DataMapperException("Error occurred reading ingredients from data source");
        }

    }

    public synchronized Ingredient findOneById(int ingredientId) throws DataMapperException {
        try {
            ResultSet rs = ingredientGateway.findOneById(ingredientId);

            while (rs.next()) {
                return getIngredientFromResultSet(rs);
            }

            return null;
        } catch (GatewayException | SQLException e) {
            throw new DataMapperException("Error occurred reading ingredients from data source");
        }
    }

    public static Ingredient getIngredientFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        float amount = rs.getFloat("amount");
        String unit = rs.getString("unit");
        String original = rs.getString("original");

        return new Ingredient(id, name, amount, unit, original);
    }
}
