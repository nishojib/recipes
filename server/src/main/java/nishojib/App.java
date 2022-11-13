package nishojib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import static spark.Spark.get;

public class App {
    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        get("recipes", (req, res) -> {
            res.type("application/json");

            try {
                List<Recipe> recipes = RecipeDataMapper.findAll();
                return gson.toJson(recipes);
            } catch (DataMapperException e) {
                System.out.println(e.getStackTrace());
                return "";
            }
        });
    }
}
