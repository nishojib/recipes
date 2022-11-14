package nishojib.recipes.models;

public class Recipe {
    private final int id;
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
