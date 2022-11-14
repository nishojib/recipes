package nishojib;

import nishojib.ingredients.IngredientController;
import nishojib.recipes.RecipeController;

public class App {
    public static void main(String[] args) {
        RecipeController recipeController = new RecipeController();
        recipeController.run();

        IngredientController ingredientController = new IngredientController();
        ingredientController.run();
    }
}
