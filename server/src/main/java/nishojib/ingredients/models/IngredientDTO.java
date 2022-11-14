package nishojib.ingredients.models;

public class IngredientDTO {
    private final String name;
    private final float amount;
    private final String unit;
    private final String original;

    public IngredientDTO(String name, float amount, String unit, String original) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.original = original;
    }

    public String getName() {
        return name;
    }

    public float getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public String getOriginal() {
        return original;
    }
}
