package nishojib.ingredients.models;

public class IngredientDTO {
    private String name;
    private float amount;
    private String unit;
    private String original;

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
