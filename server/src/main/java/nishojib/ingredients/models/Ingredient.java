package nishojib.ingredients.models;

/**
 * The Ingredient
 */
public class Ingredient {
    /**
     * The id of the ingredient
     */
    private final int id;
    /**
     * The name of the ingredient
     */
    private final String name;
    /**
     * The amount of the ingredient
     */
    private final float amount;
    /**
     * The unit of the ingredient
     */
    private final String unit;
    /**
     * The original name of the ingredient
     */
    private final String original;

    /**
     * The constructor that initializes an ingredient
     */
    public Ingredient(int id, String name, float amount, String unit, String original) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.original = original;
    }

}
