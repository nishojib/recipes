package nishojib.recipes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nishojib.core.exceptions.DataMapperException;
import nishojib.core.models.StandardResponse;
import nishojib.core.models.StatusResponse;
import nishojib.ingredients.models.Ingredient;
import nishojib.recipes.models.DeletedRecipe;
import nishojib.recipes.models.Recipe;
import nishojib.recipes.models.RecipeDTO;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

/**
 * The Recipe Controller class that gives access to all recipe based rest end points
 */
public class RecipeController {
    /**
     * The access to the recipe data mapper
     */
    RecipeDataMapper recipeDataMapper;

    /**
     * The initializer for the recipe controller
     */
    public RecipeController() {
        this.recipeDataMapper = new RecipeDataMapper();
    }

    /**
     * The run method that initializes all rest end points for Recipes
     */
    public void run() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        builder.setPrettyPrinting();

        // The get method that gets one recipe
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

        // The get method that gets the ingredients of a recipe
        get("/recipes/:recipeId/ingredients", (req, res) -> {
            res.type("application/json");

            String recipeIdStr = req.params(":recipeId");
            int recipeId = Integer.parseInt(recipeIdStr);
            List<Ingredient> ingredients;

            try {
                ingredients = recipeDataMapper.findIngredientsOfOneById(recipeId);
            } catch (DataMapperException e) {
                return gson.toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
            }

            return gson.toJson(new StandardResponse(StatusResponse.SUCCESS, gson.toJsonTree(ingredients)));
        });

        // The get method that gets all recipes
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

        // The post method to create a recipe
        post("recipes", (req, res) -> {
            res.type("application/json");

            RecipeDTO recipe = new Gson().fromJson(req.body(), RecipeDTO.class);
            recipeDataMapper.create(recipe);

            return gson.toJson(new StandardResponse(StatusResponse.SUCCESS));
        });


        // The put method that updates a recipe
        put("recipes/:recipeId", (req, res) -> {
            res.type("application/json");

            String recipeIdStr = req.params(":recipeId");
            int recipeId = Integer.parseInt(recipeIdStr);

            RecipeDTO recipe = new Gson().fromJson(req.body(), RecipeDTO.class);
            recipeDataMapper.update(recipeId, recipe);

            return gson.toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        // The delete method that deletes a recipe
        delete("recipes/:recipeId/", (req, res) -> {
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
