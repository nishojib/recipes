package nishojib.recipes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nishojib.core.exceptions.DataMapperException;
import nishojib.core.models.ErrorResult;
import nishojib.recipes.models.DeletedRecipe;
import nishojib.recipes.models.Recipe;
import nishojib.core.models.Result;

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
                return gson.toJson(new ErrorResult(e.getMessage()));
            }

            Result<Recipe> result = new Result<>(recipes);
            return gson.toJson(result);
        });

        get("/recipes", (req, res) -> {
            res.type("application/json");
            List<Recipe> recipes;

            try {
                recipes = recipeDataMapper.findAll();
            } catch (DataMapperException e) {
                return gson.toJson(new ErrorResult(e.getMessage()));
            }

            Result<Recipe> result = new Result<>(recipes);
            return gson.toJson(result);
        });

//        delete("recipes/:recipeId", (req, res) -> {
//            res.type("application/json");
//            String recipeIdStr = req.params(":recipeId");
//            int recipeId = Integer.parseInt(recipeIdStr);
//            List<DeletedRecipe> recipes = new ArrayList<>();
//
//            try {
//                boolean deleted = recipeDataMapper.deleteById(recipeId);
//                if (deleted) {
//                    DeletedRecipe recipe = new DeletedRecipe(recipeId);
//                    recipes.add(recipe);
//                } else {
//                    return gson.toJson(new ErrorResult("Unable to delete recipe with id: " + recipeId));
//                }
//            } catch (DataMapperException e) {
//                return gson.toJson(new ErrorResult(e.getMessage()));
//            }
//
//            Result<DeletedRecipe> result = new Result<>(recipes);
//            return gson.toJson(result);
//        });
    }
}
