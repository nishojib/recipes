package nishojib.ingredients.models;

public class IngredientDTO {
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
     * The initializer for the ingredient data transfer object.
     */
    public IngredientDTO(String name, float amount, String unit, String original) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.original = original;
    }

    /**
     * The getter for the name
     * @return The name of the ingredient
     */
    public String getName() {
        return name;
    }

    /**
     * The getter for the amount
     * @return The amount of the ingredient
     */
    public float getAmount() {
        return amount;
    }

    /**
     * The getter for the unit
     * @return The unit of the ingredient
     */
    public String getUnit() {
        return unit;
    }

    /**
     * The getter for the original name
     * @return The original name of the ingredient
     */
    public String getOriginal() {
        return original;
    }
}
