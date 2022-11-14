package nishojib.recipes.models;

public class RecipeDTO {
    private final String title;
    private final String image;
    private final int servings;
    private final int healthScore;
    private final boolean cheap;
    private final boolean glutenFree;
    private final boolean dairyFree;
    private final int readyInMinutes;
    private final String instructions;
    private final String summary;
    private final int[] ingredientIds;

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

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public int getServings() {
        return servings;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public boolean isCheap() {
        return cheap;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public boolean isDairyFree() {
        return dairyFree;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getSummary() {
        return summary;
    }

    public int[] getIngredientIds() {
        return ingredientIds;
    }
}
