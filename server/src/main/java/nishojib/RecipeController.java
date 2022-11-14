package nishojib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import static spark.Spark.delete;
import static spark.Spark.get;

public class RecipeController {
    RecipeDataMapper recipeDataMapper;

    public RecipeController() {
        this.recipeDataMapper = new RecipeDataMapper();
    }

    public void run() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        builder.setPrettyPrinting();

        get("/recipes/:recipeId", (req, res) -> {
            res.type("application/json");
            String recipeIdStr = req.params(":recipeId");
            int recipeId = Integer.parseInt(recipeIdStr);

            try {
                Recipe recipe = recipeDataMapper.findOneById(recipeId);
                return gson.toJson(recipe);
            } catch (DataMapperException e) {
                System.out.println(e.getMessage());
                return "";
            }
        });

        get("/recipes", (req, res) -> {
            res.type("application/json");

            try {
                List<Recipe> recipes = recipeDataMapper.findAll();
                return gson.toJson(recipes);
            } catch (DataMapperException e) {
                System.out.println(e.getMessage());
                return "";
            }
        });

        delete("recipes/:recipeId", (req, res) -> {
            res.type("application/json");
            String recipeIdStr = req.params(":recipeId");
            int recipeId = Integer.parseInt(recipeIdStr);

            try {
                boolean deleted = recipeDataMapper.deleteById(recipeId);
                if (deleted) {
                    return gson.toJson(recipeId);
                }
                return "";
            } catch (DataMapperException e) {
                System.out.println(e.getMessage());
                return "";
            }
        });
    }
}
