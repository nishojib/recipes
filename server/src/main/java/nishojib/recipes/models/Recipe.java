package nishojib.recipes.models;

/**
 * The Recipe
 */
public class Recipe {
    /**
     * The id of the recipe
     */
    private final int id;
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
     * The constructor that initializes a recipe
     */
    public Recipe(int id, String title, String image, int servings, int healthScore, int cheap, int glutenFree, int dairyFree, int readyInMinutes, String instructions, String summary) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.servings = servings;
        this.healthScore = healthScore;
        this.cheap = cheap == 1;
        this.glutenFree = glutenFree == 1;
        this.dairyFree = dairyFree == 1;
        this.readyInMinutes = readyInMinutes;
        this.instructions = instructions;
        this.summary = summary;
    }

}
