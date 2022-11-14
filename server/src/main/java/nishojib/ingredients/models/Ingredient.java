package nishojib.ingredients.models;
public class Ingredient {
    private int id;
    private String name;
    private float amount;
    private String unit;
    private String original;

    public Ingredient(int id, String name, float amount, String unit, String original) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.original = original;
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }
}
