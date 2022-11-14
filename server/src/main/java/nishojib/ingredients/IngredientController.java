package nishojib.ingredients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nishojib.ingredients.models.Ingredient;

import java.util.List;

import static spark.Spark.get;

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

    }
}
