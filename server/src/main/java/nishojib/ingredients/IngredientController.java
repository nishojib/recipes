package nishojib.ingredients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import nishojib.core.exceptions.DataMapperException;
import nishojib.core.models.ErrorResult;
import nishojib.core.models.Result;
import nishojib.ingredients.models.DeletedIngredient;
import nishojib.ingredients.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.delete;

public class IngredientController {
    IngredientDataMapper ingredientDataMapper;

    public IngredientController() {
        this.ingredientDataMapper = new IngredientDataMapper();
    }

    public void run() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        builder.setPrettyPrinting();

        get("ingredients", (req, res) -> {
            res.type("application/json");

            try {
                List<Ingredient> ingredients = ingredientDataMapper.findAll();
                return gson.toJson(ingredients);
            } catch (DataMapperException e) {
                System.out.println(e.getStackTrace());
                return "";
            }
        });

        get("ingredients/:ingredientId", (req, res) -> {
            res.type("application/json");
            String ingredientIdStr = req.params(":ingredientId");
            int ingredientId = Integer.parseInt(ingredientIdStr);

            try {
                Ingredient ingredient = ingredientDataMapper.findOneById(ingredientId);
                return gson.toJson(ingredient);
            } catch (DataMapperException e) {
                System.out.println(e.getStackTrace());
                return "";
            }
        });

        delete("ingredients/:ingredientId", (req, res) -> {
            res.type("application/json");
            String ingredientIdStr = req.params(":ingredientId");
            int ingredientId = Integer.parseInt(ingredientIdStr);
            List<DeletedIngredient> ingredients = new ArrayList<>();

            try {
                boolean deleted = ingredientDataMapper.deleteById(ingredientId);
                if (deleted) {
                    DeletedIngredient ingredient = new DeletedIngredient(ingredientId);
                    ingredients.add(ingredient);
                } else {
                    return gson.toJson(new ErrorResult("Unable to delete ingredient with id: " + ingredientId));
                }
            } catch (DataMapperException e) {
                return gson.toJson(new ErrorResult(e.getMessage()));
            }

            Result<DeletedIngredient> result = new Result<>(ingredients);
            return gson.toJson(result);
        });

    }
}
