package nishojib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import static spark.Spark.get;

public class RecipeController {

    public void run() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        builder.setPrettyPrinting();

        get("recipes", (req, res) -> {
            res.type("application/json");

            try {
                List<Recipe> recipes = RecipeDataMapper.findAll();
                return gson.toJson(recipes);
            } catch (DataMapperException e) {
                System.out.println(e.getStackTrace());
                return "";
            }
        });

        get("recipes/:recipeId", (req, res) -> {
            res.type("application/json");
            String recipeIdStr = req.params(":recipeId");
            int recipeId = Integer.parseInt(recipeIdStr);

            try {
                Recipe recipe = RecipeDataMapper.findOneById(recipeId);
                return gson.toJson(recipe);
            } catch (DataMapperException e) {
                System.out.println(e.getStackTrace());
                return "";
            }
        });

    }
}
