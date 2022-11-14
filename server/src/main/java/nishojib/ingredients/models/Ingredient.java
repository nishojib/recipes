package nishojib.ingredients.models;
public class Ingredient {
    private final int id;
    private final String name;
    private final float amount;
    private final String unit;
    private final String original;

    public Ingredient(int id, String name, float amount, String unit, String original) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.original = original;
    }

}
