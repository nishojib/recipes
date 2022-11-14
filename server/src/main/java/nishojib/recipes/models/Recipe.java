package nishojib.models;

public class Recipe {
    private int id;
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

    @Override
    public String toString() {
        return id + ": " + title;
    }
}
