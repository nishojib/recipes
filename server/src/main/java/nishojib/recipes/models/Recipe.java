package nishojib.recipes.models;

public class Recipe {

    public int getId()  {
        return id;
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
