package eg.gov.iti.jets.foodie.meals.view;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Meal;

public interface MealsViewInterface {
    public void showMeals(List<Meal> meals);

    public void showMealDetails(Meal meal);
}
