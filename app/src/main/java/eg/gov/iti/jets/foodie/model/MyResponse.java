package eg.gov.iti.jets.foodie.model;

import java.util.List;

public class MyResponse {
    private List<Meal> meals;

    public MyResponse(List<Meal> meals) {
        this.meals = meals;
    }

    public MyResponse() {
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}
