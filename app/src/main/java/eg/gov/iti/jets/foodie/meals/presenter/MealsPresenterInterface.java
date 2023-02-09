package eg.gov.iti.jets.foodie.meals.presenter;

import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.MealFavPlan;

public interface MealsPresenterInterface {
    public void addFavouriteMeal(MealFavPlan mealFavPlan);

    public void getFilteredMeals(String name, char c);

    public void getMealDetails(String id);

}
