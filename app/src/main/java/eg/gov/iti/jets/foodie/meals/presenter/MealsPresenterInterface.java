package eg.gov.iti.jets.foodie.meals.presenter;

import eg.gov.iti.jets.foodie.model.MealFavPlan;

public interface MealsPresenterInterface {
    public void addFavouriteMeal(MealFavPlan mealFavPlan);
    public void getFilteredMeals(String name,char c);

//    public void getFilteredMealsCategory();
//    public void getFilteredMealsCountry();
//    public void getFilteredMealsIngredient();
}
