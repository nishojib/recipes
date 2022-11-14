package nishojib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nishojib.exceptions.DataMapperException;
import nishojib.mappers.RecipeDataMapper;
import nishojib.models.DeletedRecipe;
import nishojib.models.Recipe;
import nishojib.models.Result;

import java.util.ArrayList;
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
            List<Recipe> recipes = new ArrayList<>();

            try {
                Recipe recipe = recipeDataMapper.findOneById(recipeId);
                recipes.add(recipe);
            } catch (DataMapperException e) {
                System.out.println(e.getMessage());
            }

            Result<Recipe> result = new Result<>(recipes);
            return gson.toJson(result);
        });

        get("/recipes", (req, res) -> {
            res.type("application/json");
            List<Recipe> recipes = new ArrayList<>();

            try {
                recipes = recipeDataMapper.findAll();
            } catch (DataMapperException e) {
                System.out.println(e.getMessage());
            }

            Result<Recipe> result = new Result<>(recipes);
            return gson.toJson(result);
        });

        delete("recipes/:recipeId", (req, res) -> {
            res.type("application/json");
            String recipeIdStr = req.params(":recipeId");
            int recipeId = Integer.parseInt(recipeIdStr);
            List<DeletedRecipe> recipes = new ArrayList<>();

            try {
                boolean deleted = recipeDataMapper.deleteById(recipeId);
                if (deleted) {
                    DeletedRecipe recipe = new DeletedRecipe(recipeId);
                    recipes.add(recipe);
                }
            } catch (DataMapperException e) {
                System.out.println(e.getMessage());
            }

            Result<DeletedRecipe> result = new Result<>(recipes);
            return gson.toJson(result);
        });
    }
}
