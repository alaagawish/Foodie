package eg.gov.iti.jets.foodie.meals.presenter;

import eg.gov.iti.jets.foodie.model.Meal;

public interface MealsPresenterInterface {
    public void addFavouriteMeal(Meal meal);

    public void getFilteredMeals(String name, char c);

    public void getMealDetails(String id);

}
