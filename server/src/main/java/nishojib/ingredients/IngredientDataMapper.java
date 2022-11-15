package nishojib.ingredients;

import nishojib.core.exceptions.DataMapperException;
import nishojib.core.exceptions.GatewayException;
import nishojib.ingredients.models.Ingredient;
import nishojib.ingredients.models.IngredientDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Data Mapper for the Ingredient
 */
public class IngredientDataMapper {
    /**
     * Access to the ingredient table data gateway
     */
    IngredientGateway ingredientGateway;

    /**
     * The initializer for the ingredient data mapper
     */
    public IngredientDataMapper() {
        ingredientGateway = new IngredientGateway();
    }

    public Ingredient getIngredientFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        float amount = rs.getFloat("amount");
        String unit = rs.getString("unit");
        String original = rs.getString("original");

        return new Ingredient(id, name, amount, unit, original);
    }

    /**
     * The method that finds and returns all ingredients
     * @return A List of Ingredient objects
     * @throws DataMapperException
     */
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

    /**
     * The method that finds and returns one ingredient by id
     * @param ingredientId The ingredient id of the ingredient
     * @return An Ingredient Object that was found using the ingredient id
     * @throws DataMapperException
     */
    public synchronized Ingredient findOneById(int ingredientId) throws DataMapperException {
        try {
            ResultSet rs = ingredientGateway.findOneById(ingredientId);

            while (rs.next()) {
                return getIngredientFromResultSet(rs);
            }

            return null;
        } catch (GatewayException | SQLException e) {
            throw new DataMapperException("Error occurred reading ingredient from data source");
        }
    }

    /**
     * The method that finds and deletes one ingredient by id
     * @param ingredientId The ingredient id of the ingredient
     * @return {true} if an ingredient is deleted, {false} otherwise
     * @throws DataMapperException
     */
    public synchronized boolean deleteById(int ingredientId) throws DataMapperException {
        try {
            ingredientGateway.deleteById(ingredientId);
            return true;
        } catch (GatewayException e) {
            throw new DataMapperException("Error occurred deleting ingredient from data source");
        }
    }

    /**
     * The method that creates an ingredient
     * @param ingredient The ingredient data transfer object
     * @throws DataMapperException
     */
    public synchronized int create(IngredientDTO ingredient) throws DataMapperException {
        try {
            int createdId = ingredientGateway.create(ingredient);
            return createdId;
        } catch (GatewayException e) {
            throw new DataMapperException("Error occurred creating ingredient in data source");
        }
    }

    /**
     * The method that updates an ingredient based on a ingredientId
     * @param ingredientId The id of the ingredient
     * @param ingredient The ingredient data transfer object
     * @throws DataMapperException
     */
    public synchronized void update(int ingredientId, IngredientDTO ingredient) throws DataMapperException {
        try {
            ingredientGateway.update(ingredientId, ingredient);
        } catch (GatewayException e) {
            throw new DataMapperException("Error occurred updating ingredient in data source");

        }
    }

}
