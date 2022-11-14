package nishojib.recipes.models;

public class RecipeDTO {
    private String title;
    private String image;
    private int servings;
    private int healthScore;
    private boolean cheap;
    private boolean glutenFree;
    private boolean dairyFree;
    private int readyInMinutes;
    private String instructions;
    private String summary;
    private int[] ingredientIds;

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
