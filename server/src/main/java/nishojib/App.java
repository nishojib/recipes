package nishojib;

public class App {
    public static void main(String[] args) {
        RecipeController recipeController = new RecipeController();
        recipeController.run();

        IngredientController ingredientController = new IngredientController();
        ingredientController.run();
    }
}
