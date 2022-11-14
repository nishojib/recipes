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

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public float getAmount() {
        return this.amount;
    }

    public String getUnit() {
        return this.unit;
    }

    public String getOriginal() {
        return this.original;
    }    

    @Override
    public String toString() {
        return id + ": " + name;
    }
}
