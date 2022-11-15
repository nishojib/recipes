package nishojib.recipes.models;

/**
 * The Data Transfer Object for the Recipe
 */
public class RecipeDTO {
    /**
     * The title of the recipe
     */
    private final String title;
    /**
     * The image of the recipe
     */
    private final String image;
    /**
     * The servings of the recipe
     */
    private final int servings;
    /**
     * The health score of the recipe
     */
    private final int healthScore;
    /**
     * Whether the recipe is cheap
     */
    private final boolean cheap;
    /**
     * Whether the recipe is gluten-free
     */
    private final boolean glutenFree;
    /**
     * Whether the recipe is dairy free
     */
    private final boolean dairyFree;
    /**
     * The time needed to complete the recipe
     */
    private final int readyInMinutes;
    /**
     * The instructions for the recipe
     */
    private final String instructions;
    /**
     * The summary of the recipe
     */
    private final String summary;
    /**
     * A list of ingredient ids of the recipe
     */
    private final int[] ingredientIds;

    /**
     * The initializer for the recipe data transfer object.
     */
    public RecipeDTO(String title, String image, int servings, int healthScore, boolean cheap, boolean glutenFree, boolean dairyFree, int readyInMinutes, String instructions, String summary, int[] ingredientIds) {
        this.title = title;
        this.image = image;
        this.servings = servings;
        this.healthScore = healthScore;
        this.cheap = cheap;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.readyInMinutes = readyInMinutes;
        this.instructions = instructions;
        this.summary = summary;
        this.ingredientIds = ingredientIds;
    }

    /**
     * The getter for the title
     * @return The title of the recipe
     */
    public String getTitle() {
        return title;
    }

    /**
     * The getter for the image
     * @return The image of the recipe
     */
    public String getImage() {
        return image;
    }

    /**
     * The getter for the servings
     * @return The servings of the recipe
     */
    public int getServings() {
        return servings;
    }

    /**
     * The getter for the health score
     * @return The health score of the recipe
     */
    public int getHealthScore() {
        return healthScore;
    }

    /**
     * The getter for the cheap
     * @return Whether the recipe is cheap or not
     */
    public boolean isCheap() {
        return cheap;
    }

    /**
     * The getter for glutenFree
     * @return Whether the recipe is gluten-free or not
     */
    public boolean isGlutenFree() {
        return glutenFree;
    }

    /**
     * The getter for dairyFree
     * @return Whether the recipe is dairy free or not
     */
    public boolean isDairyFree() {
        return dairyFree;
    }

    /**
     * The getter for the readyInMinutes
     * @return How long it takes to complete the recipe.
     */
    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    /**
     * The getter for the instructions
     * @return The instructions of the recipe
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * The getter for the summary
     * @return The summary of the recipe
     */
    public String getSummary() {
        return summary;
    }

    /**
     * The getter for the ingredient ids
     * @return The list of ingredient ids of the recipe
     */
    public int[] getIngredientIds() {
        return ingredientIds;
    }
}
