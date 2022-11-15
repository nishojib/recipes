package nishojib;

import nishojib.ingredients.IngredientController;
import nishojib.recipes.RecipeController;

import static spark.Spark.after;

public class App {
    public static void main(String[] args) {
//        after((request, response) -> {
//            response.header("Access-Control-Allow-Origin", "*");
//            response.header("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE");
//        });

        RecipeController recipeController = new RecipeController();
        recipeController.run();

        IngredientController ingredientController = new IngredientController();
        ingredientController.run();
    }
}
