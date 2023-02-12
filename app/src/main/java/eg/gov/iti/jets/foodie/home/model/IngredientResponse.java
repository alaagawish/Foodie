package eg.gov.iti.jets.foodie.home.model;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Country;
import eg.gov.iti.jets.foodie.model.IngredientList;

public class IngredientResponse {
    private List<IngredientList> meals;

    public IngredientResponse() {
    }

    public IngredientResponse(List<IngredientList> meals) {
        this.meals = meals;
    }

    public List<IngredientList> getMeals() {
        return meals;
    }

    public void setMeals(List<IngredientList> meals) {
        this.meals = meals;
    }
}
