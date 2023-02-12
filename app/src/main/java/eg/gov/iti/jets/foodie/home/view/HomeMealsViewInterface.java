package eg.gov.iti.jets.foodie.home.view;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Category;
import eg.gov.iti.jets.foodie.model.Country;
import eg.gov.iti.jets.foodie.model.IngredientList;
import eg.gov.iti.jets.foodie.model.Meal;

public interface HomeMealsViewInterface {
    public void showMeals(List<Meal> meals);

    public void showCountries(List<Country> countries);

    public void showCategories(List<Category> categories);

    public void showIngrediants(List<IngredientList> ingredient);

    public void addMeal(Meal meal);


    public void showMealDetails(Meal meal);
}
