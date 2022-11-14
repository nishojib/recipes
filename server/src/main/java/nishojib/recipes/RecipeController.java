package nishojib.recipes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nishojib.core.exceptions.DataMapperException;
import nishojib.core.models.StandardResponse;
import nishojib.core.models.StatusResponse;
import nishojib.recipes.models.DeletedRecipe;
import nishojib.recipes.models.Recipe;
import nishojib.recipes.models.RecipeDTO;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

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
                if (recipe != null) {
                    recipes.add(recipe);
                }
            } catch (DataMapperException e) {
                return gson.toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
            }

            return gson.toJson(new StandardResponse(StatusResponse.SUCCESS, gson.toJsonTree(recipes)));
        });

        get("/recipes", (req, res) -> {
            res.type("application/json");
            List<Recipe> recipes;

            try {
                recipes = recipeDataMapper.findAll();
            } catch (DataMapperException e) {
                return gson.toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
            }

            return gson.toJson(new StandardResponse(StatusResponse.SUCCESS, gson.toJsonTree(recipes)));
        });

        post("recipes", (req, res) -> {
            res.type("application/json");
            RecipeDTO recipe = new Gson().fromJson(req.body(), RecipeDTO.class);
            recipeDataMapper.create(recipe);

            return gson.toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        put("recipes", (req, res) -> {
            res.type("application/json");
            Recipe recipe = new Gson().fromJson(req.body(), Recipe.class);
            recipeDataMapper.updateRecipe(recipe);

            return gson.toJson(new StandardResponse(StatusResponse.SUCCESS));
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
                } else {
                    return gson.toJson(new StandardResponse(StatusResponse.ERROR, "Unable to delete recipe with id: " + recipeId));
                }
            } catch (DataMapperException e) {
                return gson.toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
            }

            return gson.toJson(new StandardResponse(StatusResponse.SUCCESS, gson.toJsonTree(recipes)));
        });
    }
}
